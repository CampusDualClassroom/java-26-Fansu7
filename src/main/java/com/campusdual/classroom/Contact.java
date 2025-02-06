package com.campusdual.classroom;

import com.campusdual.util.Utils;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;

public class Contact implements ICallActions {

  private String name;
  private String surnames;
  private String phone;
  private String code;
  private List<String> secondaryPhones;

  public Contact(final String name, final String surnames, final String phone) {
    this.name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    this.surnames = Normalizer.normalize(surnames, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    this.phone = phone;
    this.code = generateContactCode(this.name, this.surnames);
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getSurnames() {
    return this.surnames;
  }

  public void setSurnames(final String surnames) {
    this.surnames = surnames;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(final String phone) {
    this.phone = phone;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public List<String> getSecondaryPhones() {
    return this.secondaryPhones;
  }

  @Override
  public void callMyNumber() {
    System.out.println("Llamando a: " + this.getName() + " " + this.getSurnames());
    System.out.println("Tlf: " + this.getPhone());
  }

  @Override
  public void callOtherNumber(final String number) {
    System.out.println(this.getName() + " " + this.getSurnames() + " llamando al " + number);
  }

  @Override
  public void showContactDetails() {
    StringBuilder sb = new StringBuilder();
    sb.append("Nombre: ").append(getName()).append("\n");
    sb.append("Apellido: ").append(getSurnames()).append("\n");
    sb.append("Teléfono: ").append(getPhone()).append("\n");
    sb.append("Código: ").append(getCode()).append("\n");
    System.out.println(sb.toString());
  }

  public String generateContactCode(String nombre, String apellidos) {
    // Convertir a minúsculas
    nombre = nombre.toLowerCase();
    apellidos = apellidos.toLowerCase();

    // Eliminar signos diacríticos (acentos)
    nombre = Normalizer.normalize(nombre, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    apellidos = Normalizer.normalize(apellidos, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

    // Separar el nombre y apellidos
    String[] apellidosPartes = apellidos.split("\\s+");  // Para manejar apellidos compuestos

    // Tomamos solo la primera letra del primer nombre
    String codigoUsuario = String.valueOf(nombre.charAt(0));

    if(apellidosPartes.length >= 2){
      for (int i = 0; i < apellidosPartes.length; i++) {
        if(i!=0){
          codigoUsuario += apellidosPartes[i];
        }else{
          codigoUsuario += apellidosPartes[i].charAt(0);
        }
      }
    }else{
      codigoUsuario+= apellidosPartes[0];
    }

    return codigoUsuario;
  }

}
