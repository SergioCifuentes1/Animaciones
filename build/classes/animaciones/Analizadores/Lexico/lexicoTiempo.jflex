package animaciones.Analizadores.Lexico;
import java_cup.runtime.Symbol;
import animaciones.Analizadores.Sintactico.SimbolosTiempo;
%%
%class AnalizadorLexicoTiempo
%cupsym SimbolosTiempo
%cup
%cupdebug
%line
%column

/*Identifiers*/
Letra = [a-zA-Z]
Signo = [!@#$%&*]
Digito = [0123456789]
%%
//Reglas Lexicas
<YYINITIAL>{    
        "TIEMPOS"                                  {return new Symbol(SimbolosTiempo.TIEMPOS, yycolumn,yyline,yytext());}
            "duracion"                          {return new Symbol(SimbolosTiempo.DURACION, yycolumn,yyline,yytext());}
            "imagenes"                                  {return new Symbol(SimbolosTiempo.IMAGENES, yycolumn,yyline,yytext());}
            "id"                                  {return new Symbol(SimbolosTiempo.ID, yycolumn,yyline,yytext());}        
            "inicio"                                   {return new Symbol(SimbolosTiempo.INICIO, yycolumn,yyline,yytext());}
            "fin"                                  {return new Symbol(SimbolosTiempo.FIN, yycolumn,yyline,yytext());}
        "{"                                     {return new Symbol(SimbolosTiempo.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosTiempo.LLAVE_C, yycolumn,yyline,yytext());}
        "["                                     {return new Symbol(SimbolosTiempo.CORCHETE_A, yycolumn,yyline,yytext());}
        "]"                                     {return new Symbol(SimbolosTiempo.CORCHETE_C, yycolumn,yyline,yytext());}
        ":"                                     {return new Symbol(SimbolosTiempo.DOS_PUNTOS, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosTiempo.COMA, yycolumn,yyline,yytext());} 
        "\""|”                                   {return new Symbol(SimbolosTiempo.COMILLAS, yycolumn,yyline,yytext());}     
         ({Letra}|"_") ({Letra}|"_"|{Digito})*                {return new Symbol(SimbolosTiempo.Id, yycolumn,yyline,yytext()); }
        ({Digito})+                                     {return new Symbol(SimbolosTiempo.Entero, yycolumn,yyline,yytext());}
        [ \t\r\f\n]                            {} 
        .                                            {return new Symbol(SimbolosTiempo.ERROR,yycolumn,yyline,yytext());}

}

