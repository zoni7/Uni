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
        private int idPreguntaAnteriorTipo;
        private Instructor instructor;
        private string tipoPregunta;
        private const int MAXOPCIONES = 4;
        private const int MAXOPCIONESCORRECTAS = 1;
        private bool modicandoPreguntaVF = false;


        public FormularioPreguntas(Quiz quiz = null)
        {
            InitializeComponent();
            LoadData();
            PermitirAñadirOpciones(false);
            if (quiz == null) this.quiz = new Quiz();
            else this.quiz = quiz;

        }
        

        private void LoadData()
        {
            GLOBALES = GLOBALES.Instancia;
            instructor = (Instructor)GLOBALES.UsuarioActivo;
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
            pregunta.Opciones.Add(o);
            ListaOpciones.Items.Add(o);

            HayDemasiadasOpciones();
            EstaFormularioCompleto();            
        }

        
        private void BotonBorrarOpcion_Click(object sender, RoutedEventArgs e)
        {
            pregunta.Opciones.Remove((Opcion)ListaOpciones.SelectedItem);
            ListaOpciones.Items.Remove(ListaOpciones.SelectedItem);            
            EstaFormularioCompleto();
            HayDemasiadasOpciones();
        }
        #endregion

        #region Cancelar Añadir Pregunta
        private bool CreandoPregunta()
        {
            return !ModificandoPregunta();
        }
        private bool ModificandoPregunta()
        {
            return GLOBALES.servicio.ExistePregunta(pregunta) || idPreguntaAnteriorTipo != 0;
        }        
        private void BotonAñadir_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (CreandoPregunta())
                {                    
                    
                    if (GLOBALES.servicio.ExisteQuiz(quiz))
                    {
                        GLOBALES.servicio.AñadirPregunta(pregunta);
                        // Crear una copia y asignala al quiz
                        Pregunta copiaPregunta = quiz.crearPregunta(CajaEnunciado.Text, instructor, tipoPregunta);
                        foreach (Opcion o in pregunta.Opciones)
                        {
                            copiaPregunta.Opciones.Add(o);
                        }
                        GLOBALES.servicio.AñadirPregunta(copiaPregunta);                        
                        GLOBALES.servicio.AñadirPreguntaAQuiz(quiz, copiaPregunta);
                    }
                    else
                    {                       
                        GLOBALES.servicio.AñadirPregunta(pregunta);
                        // Modifica por si se ha modificado el enunciado después de seleccionar el tipo de pregunta
                        GLOBALES.servicio.ModificarPregunta(pregunta, CajaEnunciado.Text);
                    }
                }
                else if (ModificandoPregunta())
                {

                    Pregunta preguntaAnteriorTipo = GLOBALES.servicio.EncontarPreguntaID(idPreguntaAnteriorTipo);
                    // Si se està modificando el tipo de pregunta, borrar la anterior para crear una nueva con el nuevo tipo                   
                    GLOBALES.servicio.BorrarPregunta(preguntaAnteriorTipo);
                    GLOBALES.servicio.AñadirPregunta(pregunta);
                    GLOBALES.servicio.ModificarPregunta(pregunta, CajaEnunciado.Text);                                                                       
                   
                }
                this.Close();

            } catch(ServiceException error)
            {
                MessageBox.Show(error.Message, "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            
        }

        private void BotonCancelar_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
        #endregion

        #region ComoBox Tipo Pregunta

        private void PermitirAñadirOpciones(bool b)
        {          
            CajaRespuesta.IsEnabled = b;
            BotonAñadirOpcion.IsEnabled = b && CajaRespuesta.Text != "";
            BotonBorrarOpcion.IsEnabled = b && ListaOpciones.SelectedItem != null;
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
                
                tipoPregunta = "Abierta";
                pregunta = quiz.crearPregunta(CajaEnunciado.Text, instructor, tipoPregunta);
                // Crear la unica opción abierta y añadirla a la preunta
                Opcion opcionAbierta = pregunta.CrearOpcion("", 0, false);
                pregunta.Opciones.Add(opcionAbierta);
                ListaOpciones.IsEnabled = false;
                PermitirAñadirOpciones(false);
                
            }
            else if (ComboBoxTipoPregunta.SelectedItem.Equals(Cerrada))
            {
                ListaOpciones.IsEnabled = true;
                tipoPregunta = "Cerrada";
                pregunta = quiz.crearPregunta(CajaEnunciado.Text, instructor, tipoPregunta);
                PermitirAñadirOpciones(true);
                

            }
            else if (ComboBoxTipoPregunta.SelectedItem.Equals(Verdadero_o_Falso) )
            {
                ListaOpciones.IsEnabled = true;
                tipoPregunta = "VerdaderoFalso";
                pregunta = quiz.crearPregunta(CajaEnunciado.Text, instructor, tipoPregunta);
                PermitirAñadirOpciones(false);

                // Si modificas una pregunta VF no hay que añadir otra vez las opciones
                if (modicandoPreguntaVF) { modicandoPreguntaVF = false; return; }
               
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
                pregunta.Opciones.Add(opcionVerdadero);
                Opcion opcionFalso = pregunta.CrearOpcion("Falso", 0, !correcta);
                pregunta.Opciones.Add(opcionFalso);
                ListaOpciones.Items.Add(opcionVerdadero);
                ListaOpciones.Items.Add(opcionFalso);

                
            }

            // Comprovar si el formulario está completo para poder Añadir
            EstaFormularioCompleto();
            // Comprovar que la opcion esta escrita para poder añadir una
            EstaOpcionEscrita();
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
            
            this.idPreguntaAnteriorTipo = preguntaSelecionada.Id;
            CajaEnunciado.Text = preguntaSelecionada.Enunciado;
            if (preguntaSelecionada is PreguntaAbierta)
            {
                ComboBoxTipoPregunta.SelectedItem = Abierta;
            } else if (preguntaSelecionada is PreguntaVF)
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
        }

        #endregion

        #region Detectores de eventos
        private bool HayDemasiadasOpciones() 
        {
            if (ListaOpciones.Items.Count >= MAXOPCIONES)
            {
                BotonAñadirOpcion.IsEnabled = false;
                return true;
            }
            else
            {
                BotonAñadirOpcion.IsEnabled = true;
                return false;
            }
        }
        private void EstaOpcionEscrita()
        {
            // La lista no deve superar el máximo de opciones
            if (HayDemasiadasOpciones()) return;

            if ( CajaRespuesta.Text != "")
            {
                BotonAñadirOpcion.IsEnabled = true;
            } else
            {
                BotonAñadirOpcion.IsEnabled = false;
            }
        }
        private void EstaFormularioCompleto()
        {            
            if (CajaEnunciado.Text != "" && ComboBoxTipoPregunta.SelectedItem != null && HayOpcionAñadida() && HayUnaOpcionCorrecta())
            {
                BotonAñadir.IsEnabled = true;
                
            }
            else BotonAñadir.IsEnabled = false;
            
        }

        private bool HayOpcionAñadida()
        {
            if (pregunta is PreguntaCerrada || pregunta is PreguntaAbierta)
            {
                return pregunta.Opciones.Count > 0;            
            } else
            {
                return false;
            }
        }

        private bool HayUnaOpcionCorrecta()
        {
            int contador= 0;
            if (pregunta is PreguntaCerrada)
            {
                foreach (OpcionCerrada opcion in pregunta.Opciones)
                {
                    if (opcion.EsCorrecta) contador++;
                }
                if (contador == MAXOPCIONESCORRECTAS) return true;                
                else return false;
            }
            else if (pregunta is PreguntaAbierta) { return true; }
            else return false;
        }
        #endregion

        #region Handlers
        private void TextChanged(object sender, TextChangedEventArgs e)
        {
            EstaOpcionEscrita();
            EstaFormularioCompleto();
        }
        #endregion



        private void ListaOpciones_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
            var item = (sender as ListView).SelectedItem;
            if (item != null) BotonBorrarOpcion.IsEnabled = true;
            else BotonBorrarOpcion.IsEnabled = false;
        }
    }
}
