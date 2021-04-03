package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
	
	private final StringProperty Nombre = new SimpleStringProperty();
	private final StringProperty Apellidos = new SimpleStringProperty();
        private final StringProperty Image = new SimpleStringProperty();
		
	public Persona(String nombre, String apellidos, String image)
	{
		Nombre.setValue(nombre);
		Apellidos.setValue(apellidos);
                Image.setValue(image);
	}
	
	public final StringProperty NombreProperty() {
		return this.Nombre;
	}
	public final java.lang.String getNombre() {
		return this.NombreProperty().get();
	}
	public final void setNombre(final java.lang.String Nombre) {
		this.NombreProperty().set(Nombre);
	}
	public final StringProperty ApellidosProperty() {
		return this.Apellidos;
	}
	public final java.lang.String getApellidos() {
		return this.ApellidosProperty().get();
	}
	public final void setApellidos(final java.lang.String Apellidos) {
		this.ApellidosProperty().set(Apellidos);
	}
        
        public String getPathImage() {
        return Image.get();
        }

        public void setPathImage(String value) {
            Image.set(value);
        }

        public StringProperty pathImageProperty() {
            return Image;
        }

}