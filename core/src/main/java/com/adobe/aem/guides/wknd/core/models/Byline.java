package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

public interface Byline{

    /* @return un string que sea el nombre que se muestra */
    String getName();

    /* Las occupatiosn se devuelven alfabeticamente, nos devuelve una lista*/
    List<String> getOccupations();
    
    /*Devolvemos un booleano si el componente se puede displayear y no esta vacio */
    boolean isEmpty();
}
