package animaciones.Analizadores.Lexico;
import java_cup.runtime.Symbol;
import animaciones.Analizadores.Sintactico.SimbolosLienzo;
%%
%class AnalizadorLexicoLienzo
%cupsym SimbolosLienzo
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
        "LIENZOS"                                  {return new Symbol(SimbolosLienzo.LIENZOS, yycolumn,yyline,yytext());}
            "nombre"                                         {return new Symbol(SimbolosLienzo.NOMBRE, yycolumn,yyline,yytext());}
            "tamaño"                                     {return new Symbol(SimbolosLienzo.TAMAÑO, yycolumn,yyline,yytext());}
                    "cuadro"                                         {return new Symbol(SimbolosLienzo.CUADRO, yycolumn,yyline,yytext());}
            "dimension_x"                                     {return new Symbol(SimbolosLienzo.DIM_X, yycolumn,yyline,yytext());}
            "dimension_y"                                     {return new Symbol(SimbolosLienzo.DIM_Y, yycolumn,yyline,yytext());}
            "tipo"                                     {return new Symbol(SimbolosLienzo.TIPO, yycolumn,yyline,yytext());}
                "png"                                          {return new Symbol(SimbolosLienzo.PNG, yycolumn,yyline,yytext());}
                "gif"                                         {return new Symbol(SimbolosLienzo.GIF, yycolumn,yyline,yytext());}
            "Fondo"                          {return new Symbol(SimbolosLienzo.FONDO, yycolumn,yyline,yytext());}
            "HEX:"                          {return new Symbol(SimbolosLienzo.HEX, yycolumn,yyline,yytext());}
            "Red"                                  {return new Symbol(SimbolosLienzo.RED, yycolumn,yyline,yytext());}
            "Blue"                                   {return new Symbol(SimbolosLienzo.BLUE, yycolumn,yyline,yytext());}
            "Green"                                  {return new Symbol(SimbolosLienzo.GREEN, yycolumn,yyline,yytext());}
        "{"                                     {return new Symbol(SimbolosLienzo.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosLienzo.LLAVE_C, yycolumn,yyline,yytext());}
        ":"                                     {return new Symbol(SimbolosLienzo.DOS_PUNTOS, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosLienzo.COMA, yycolumn,yyline,yytext());}
       "\""                                     {return new Symbol(SimbolosLienzo.COMILLAS, yycolumn,yyline,yytext());}
        
        ({Digito}){1,3}                                 {return new Symbol(SimbolosLienzo.NumRGB, yycolumn,yyline,yytext());}
        ("#")(("A"|"a"|"B"|"b"|"C"|"c"|"D"|"d"|"E"|"e"|"F"|"f"|{Digito}){6,6})        {return new Symbol(SimbolosLienzo.NumHEX, yycolumn,yyline,yytext());}
        ({Letra}|"_") ({Letra}|"_   "|{Digito})*                {return new Symbol(SimbolosLienzo.Id, yycolumn,yyline,yytext()); }
        ({Digito})+                                     {return new Symbol(SimbolosLienzo.Entero, yycolumn,yyline,yytext());}
({Letra}|{Signo}|{Digito})({Letra}|{Signo}|{Digito}|" ")+                {return new Symbol(SimbolosLienzo.Nombre, yycolumn,yyline,yytext()); }
        [ \t\r\f\n]                            {} 
            
        .                                            {return new Symbol(SimbolosLienzo .ERROR,yycolumn,yyline,yytext());}

}