package com.campusdual.classroom;

import com.campusdual.util.Utils;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {

  private Map<String,Contact> customMap = new HashMap<>();

  public Map<String,Contact> getData(){
    return this.customMap;
  }

  public boolean addContact(Contact contact){
    customMap.put(contact.getCode(), contact);
    return true;
  }

  public void showContact(String id){
    if(customMap.containsKey(id)){
      Contact searchedContact = customMap.get(id);
      StringBuilder sb = new StringBuilder();
      sb.append("Nombre: ").append(searchedContact.getName()).append("\n");
      sb.append("Apellido: ").append(searchedContact.getSurnames()).append("\n");
      sb.append("Teléfono: ").append(searchedContact.getPhone());
      System.out.println(sb.toString());
    }else{
      System.out.println("Ese contacto no se encuentra en la guia telefónica");
    }
  }

  public void showPhonebook(){
    StringBuilder sb = new StringBuilder();
    int menuIndex = 1;
    for(Map.Entry<String, Contact> e: customMap.entrySet()){
      sb.append("\n").append(menuIndex).append(".");
      sb.append("Nombre: ").append(e.getValue().getName()).append("\n");
      sb.append("Apellido: ").append(e.getValue().getSurnames()).append("\n");
      sb.append("Teléfono: ").append(e.getValue().getPhone()).append("\n");
      sb.append("Codigo de contacto: ").append(e.getValue().getCode()).append("\n");
      menuIndex+=1;
    }
    System.out.println(sb.toString());
  }

  public void deleteContact(String idContact){
    customMap.remove(idContact);
  }

  public void phonebookMenu(){
    String userInput;
    do{
      userInput = Utils.string("\n-Escoge un contacto utilizando su código \n-Añade un contacto introduciendo \"add\" (Cancelar -> 'C'): ").toLowerCase();
      if(userInput.equals("add")){
        if(addContact(new Contact(Utils.string("\nNombre: "), Utils.string("\nApellidos: "), Utils.string("\nTelefono: ")))){
          System.out.println("Contacto añadido a la agenda.");
        }else{
          System.out.println("Error al añadir un contacto, vuelva a intentarlo.");
        }
      } else if (userInput.equals("c")) {
        System.out.println("Cerrando agenda...");
      } else{
        if(customMap.containsKey(userInput)){
          contactMenu(customMap.get(userInput));
        }else{
          System.out.println("El contacto no existe en la agenda.");
        }
      }
    }while(!userInput.equals("c"));
  }

  public void contactMenu(Contact contact) {
    StringBuilder sb = new StringBuilder();
    sb.append("Escoge una opción: ");
    sb.append("\n").append("1").append(".");
    sb.append("Ver detalles del contacto.");
    sb.append("\n").append("2").append(".");
    sb.append("Llamar al contacto.");
    sb.append("\n").append("3").append(".");
    sb.append("Borrar contacto.");
    sb.append("\n").append("4").append(".");
    sb.append("Salir\n");
    Boolean validar;
    do {
      System.out.println(sb.toString());
      validar = validarMenuContact(Utils.string(), contact);
    }while(validar);
  }

  public Boolean validarMenuContact(String opcion, Contact contacto){
    switch (opcion){
      case ("1"):
        contacto.showContactDetails();
        return true;
      case("2"):
        System.out.println("Llamando al " + contacto.getPhone() + "...");
        return true;
      case("3"):
        deleteContact(contacto.getCode());
        return false;
      default:
        return false;
    }
  }
}
