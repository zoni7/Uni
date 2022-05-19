using ClassLibrary.Entities;
using ClassLibrary.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using ClassLibrary.BusinessLogic;
using System.Text.RegularExpressions;

namespace UIQuizify
{
    /// <summary>
    /// Lógica de interacción para FormularioPreguntas.xaml
    /// </summary>
    public partial class FormularioPreguntas : Window
    {
        private GLOBALES GLOBALES;
        private Quiz quiz;
        private Pregunta pregunta;
        private int idPreguntaCargada;
        private Instructor instructor;
        private Bateria Bateria;
        private string tipoPregunta;
        private const int MAXOPCIONES = 4;
        private const int MAXOPCIONESCORRECTAS = 1;
        private bool modicandoPreguntaVF = false;


        public FormularioPreguntas(Quiz quiz = null, Bateria bateria = null)
        {
            InitializeComponent();
            this.Title = "Crear nueva pregunta";
            LoadData();            
            if (quiz == null) this.quiz = new Quiz();           
            else this.quiz = quiz;
            this.pregunta = new PreguntaCerrada();
            this.Bateria = bateria;

            // Checkers
            PermitirGestionarOpciones();
            PermitirGestionarPreguntas();
        }
        

        private void LoadData()
        {
            GLOBALES = GLOBALES.Instancia;
            instructor = (Instructor)GLOBALES.UsuarioActivo;

            // Valores por defecto
            ComboBoxTipoPregunta.SelectedItem = Cerrada;
            CajaNumMaxChars.Text = "140";
        }

        #region Gestion de opciones
        // Botones relacionados con Respuesta (opciones)
        private void BotonAñadirOpcion_Click(object sender, RoutedEventArgs e)
        {

        
            // Preguntar si es la opcion correcta
            bool correcta;
            if (MessageBox.Show("¿Es la opcion correcta?", "Question", MessageBoxButton.YesNo, MessageBoxImage.Information) == MessageBoxResult.No)
            {
                correcta = false;
            }
            else
            {
                correcta = true;
                // Si ya hay una opcion correcta no se puede añadir ninguna más
                if (HayUnaOpcionCorrecta())
                {
                    MessageBox.Show("Solo puede haber una opción correcta", "Información", MessageBoxButton.OK, MessageBoxImage.Warning);
                    return;
                }
            }
            Opcion o = pregunta.CrearOpcion(CajaRespuesta.Text,0,correcta);            
            ListaOpciones.Items.Add(o);

            // Checkers
            PermitirGestionarOpciones();
            PermitirGestionarPreguntas();            
        }

        
        private void BotonBorrarOpcion_Click(object sender, RoutedEventArgs e)
        {
            pregunta.Opciones.Remove((Opcion)ListaOpciones.SelectedItem);
            ListaOpciones.Items.Remove(ListaOpciones.SelectedItem);

            // Checkers
            PermitirGestionarOpciones();
            PermitirGestionarPreguntas();            
        }
        #endregion

        #region Cancelar Añadir Pregunta
        private bool CreandoPregunta()
        {
            return !ModificandoPregunta();
        }
        private bool ModificandoPregunta()
        {
            return GLOBALES.fachada.ExistePregunta(pregunta) || idPreguntaCargada != 0;
        }
        private void AñadirOpciones(string tipo)
        {
            if (tipo == "PreguntaAbierta")
            {
                if (CreandoPregunta())
                {
                    Opcion opcionAbierta = pregunta.CrearOpcion(CajaInstrucciones.Text, Int32.Parse(CajaNumMaxChars.Text), false);
                    pregunta.Opciones.Add(opcionAbierta);
                } else
                {
                    OpcionAbierta opcionAbierta = (OpcionAbierta)pregunta.Opciones.First();
                    GLOBALES.fachada.ModificarOpcionAbierta(opcionAbierta, CajaInstrucciones.Text, Int32.Parse(CajaNumMaxChars.Text) );
                }
                
            } else
            {
                foreach (Opcion o in ListaOpciones.Items)
                {
                    if (!pregunta.Opciones.Contains(o)) 
                    {
                        o.Pregunta = pregunta;
                        pregunta.Opciones.Add(o);
                    }
                   
                }
            }
        }
        
        private void BotonAñadir_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (CreandoPregunta())
                {

                    if (GLOBALES.fachada.ExisteQuiz(quiz))
                    {
                        pregunta = quiz.crearPregunta(CajaEnunciado.Text, instructor, tipoPregunta);
                        AñadirOpciones(tipoPregunta);
                        GLOBALES.fachada.AñadirPregunta(pregunta);
                        // Crear una copia y asignala al quiz
                        Pregunta copiaPregunta = (Pregunta) pregunta.Clonar();
                        GLOBALES.fachada.AñadirPregunta(copiaPregunta);
                        GLOBALES.fachada.AñadirPreguntaAQuiz(quiz, copiaPregunta);
                    }
                    else
                    {
                        pregunta = quiz.crearPregunta(CajaEnunciado.Text, instructor, tipoPregunta);
                        AñadirOpciones(tipoPregunta);
                        GLOBALES.fachada.AñadirPregunta(pregunta);        
                        
                        if (Bateria != null)
                        {
                            GLOBALES.fachada.AñadirPreguntaABateria(pregunta, Bateria);
                        }
                    }
                }
                else if (ModificandoPregunta())
                {
                    AñadirOpciones(tipoPregunta);
                    GLOBALES.fachada.ModificarPregunta(pregunta, CajaEnunciado.Text);
                }
                this.Close();
            } catch(ServiceException error)
            {
                MessageBox.Show(error.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            this.Close();
        }

        private void BotonVolver_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
        #endregion

        #region ComoBox Tipo Pregunta   
        private void CambiarGridContenidoPregunta() 
        { 
            switch (tipoPregunta)
            {
                case "PreguntaAbierta":
                    GridContenidoAbierta.Visibility = Visibility.Visible;
                    GridContenidoCerrada.Visibility = Visibility.Hidden;
                    break;
                case "PreguntaCerrada":
                    GridContenidoAbierta.Visibility = Visibility.Hidden;
                    GridContenidoCerrada.Visibility = Visibility.Visible;
                    GridContenidoCerrada_noVF.Visibility = Visibility.Visible;
                    break;
                case "PreguntaVF":
                    GridContenidoAbierta.Visibility = Visibility.Hidden;
                    GridContenidoCerrada.Visibility = Visibility.Visible;
                    GridContenidoCerrada_noVF.Visibility = Visibility.Hidden;
                    break;
                default:
                    GridContenidoAbierta.Visibility = Visibility.Hidden;
                    GridContenidoCerrada.Visibility = Visibility.Hidden;
                    break;
            }
               
        }
        // Crear Preguntas cuando se seleccione el tipo     
        private void ComboBoxTipoPregunta_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {                        
            // Limpia lista de opciones disponibles
            ListaOpciones.Items.Clear();
            // Limpiar la caja de opciones cada vez que cambias el tipo de pregunta
            CajaRespuesta.Text = "";
            
            if (ComboBoxTipoPregunta.SelectedItem.Equals(Abierta))
            {                
                tipoPregunta = "PreguntaAbierta";                                                               
                
            }
            else if (ComboBoxTipoPregunta.SelectedItem.Equals(Cerrada))
            {                
                tipoPregunta = "PreguntaCerrada";                                               
            }
            else if (ComboBoxTipoPregunta.SelectedItem.Equals(Verdadero_o_Falso) )
            {                
                tipoPregunta = "PreguntaVF";
                CambiarGridContenidoPregunta();
                // Si modificas una pregunta VF no hay que añadir otra vez las opciones
                if (modicandoPreguntaVF) { modicandoPreguntaVF = false;  }
                else
                {
                    // Preguntar si es la opcion correcta
                    bool correcta;
                    if (MessageBox.Show("¿La pregunta es verdadera?", "Question", MessageBoxButton.YesNo, MessageBoxImage.Question) == MessageBoxResult.No)
                    {
                        correcta = false;
                    }
                    else
                    {
                        correcta = true;
                    }

                    Opcion opcionVerdadero = pregunta.CrearOpcion("Verdadero", 0, correcta);
                    Opcion opcionFalso = pregunta.CrearOpcion("Falso", 0, !correcta);
                    ListaOpciones.Items.Add(opcionVerdadero);
                    ListaOpciones.Items.Add(opcionFalso);
                }
               
                

                
            }
            CambiarGridContenidoPregunta();
            // Checkers
            PermitirGestionarPreguntas();
            PermitirGestionarOpciones();
            
        }

        #endregion

        #region cargar pregunta a Modificar
        private void RellenarListaOpciones(Pregunta preguntaSelecionada)
        {
            foreach (Opcion o in preguntaSelecionada.Opciones)
            {
                pregunta.Opciones.Add(o);
                ListaOpciones.Items.Add(o);
            }
        }
        public void CargarDatosParaModificar(Pregunta preguntaSelecionada)
        {
            
            this.idPreguntaCargada = preguntaSelecionada.Id;
            CajaEnunciado.Text = preguntaSelecionada.Enunciado;
            
            if (preguntaSelecionada is PreguntaAbierta)
            {                
                ComboBoxTipoPregunta.SelectedItem = Abierta;
                OpcionAbierta opcionAbierta = (OpcionAbierta)preguntaSelecionada.Opciones.First();
                CajaInstrucciones.Text = opcionAbierta.Texto;               
                CajaNumMaxChars.Text = opcionAbierta.NumCaracteresMax.ToString();
            }
            else if (preguntaSelecionada is PreguntaVF)
            {
                modicandoPreguntaVF = true; // Flag
                ComboBoxTipoPregunta.SelectedItem = Verdadero_o_Falso;
                
            } else 
            {
                ComboBoxTipoPregunta.SelectedItem = Cerrada;               
            }

            RellenarListaOpciones(preguntaSelecionada);
            BotonAñadir.IsEnabled = false;
            BotonAñadirOpcion.IsEnabled = false;
            BotonAñadir.Content = "Modificar";
            BotonAñadir.IsEnabled = true;
            ComboBoxTipoPregunta.IsEnabled = false;

            // Cargar la pregunta
            pregunta = preguntaSelecionada;
            // Checkers
            PermitirGestionarOpciones();
            PermitirGestionarPreguntas();
        }

        #endregion

        #region Detectores de eventos
        private bool HayDemasiadasOpciones() 
        {
            if (ListaOpciones.Items.Count >= MAXOPCIONES)
            {                
                return true;
            }
            else
            {                
                return false;
            }
        }
        private bool HayOpcionEscrita()
        {            
            if ( CajaRespuesta.Text != "")
            {
                return true;
            } else
            {
                return false;
            }
        }
        

        private bool HayOpcionAñadida()
        {
            if (ComboBoxTipoPregunta.SelectedItem.Equals(Abierta)) return true;
            else return ListaOpciones.Items.Count > 0;
        }

        private bool HayUnaOpcionCorrecta()
        {
            int contador= 0;
            if (ComboBoxTipoPregunta.SelectedItem.Equals(Cerrada))
            {
                foreach (OpcionCerrada opcion in ListaOpciones.Items)
                {
                    if (opcion.EsCorrecta) contador++;
                }
                if (contador == MAXOPCIONESCORRECTAS) return true;
                else return false;
            }
            else if (ComboBoxTipoPregunta.SelectedItem.Equals(Verdadero_o_Falso)) return true;
            else if (ComboBoxTipoPregunta.SelectedItem.Equals(Abierta))  return true; 
            else return false;
        }

        private bool HayNumMaxChars()
        {
            if (ComboBoxTipoPregunta.SelectedItem.Equals(Abierta))
            {
                return CajaNumMaxChars.Text != "";
            }
            else return true;
        }
        #endregion      

        #region checkers
        // Gestion CRUD Opciones
        private void PermitirGestionarOpciones()
        {
            if (!HayDemasiadasOpciones() && HayOpcionEscrita())
            {
                BotonAñadirOpcion.IsEnabled = true;                
            }
            else
            {
                BotonAñadirOpcion.IsEnabled = false;                
            }

            if (ListaOpciones.Items.Count > 0 && ListaOpciones.SelectedItem != null)
            {
                BotonBorrarOpcion.IsEnabled = true;
            } else
            {
                BotonBorrarOpcion.IsEnabled = false;
            }
        }

        // Gestion CRUD Preguntas
        private void PermitirGestionarPreguntas()
        {
            if (CajaEnunciado.Text != "" && ComboBoxTipoPregunta.SelectedItem != null && HayOpcionAñadida()
                && HayUnaOpcionCorrecta() && HayNumMaxChars())
            {
                BotonAñadir.IsEnabled = true;

            }
            else BotonAñadir.IsEnabled = false;
        }

        

        #endregion

        #region Handlers
        private void TextChanged(object sender, TextChangedEventArgs e)
        {
            PermitirGestionarOpciones();
            PermitirGestionarPreguntas();
        }
        private void ListaOpciones_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
            PermitirGestionarOpciones();
        }
        
        private void NumberValidationTextBox(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^0-9]+");
            e.Handled = regex.IsMatch(e.Text);
           
        }        

        #endregion
        
    }
}
