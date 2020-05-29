package animaciones.Analizadores.Lexico;
import java_cup.runtime.Symbol;
import animaciones.Analizadores.Sintactico.SimbolosColores;
%%
%class AnalizadorLexicoColores
%cupsym SimbolosColores
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
        "COLORES"                                  {return new Symbol(SimbolosColores.COLORES, yycolumn,yyline,yytext());}
            "HEX:"                          {return new Symbol(SimbolosColores.HEX, yycolumn,yyline,yytext());}
            "Red"                                  {return new Symbol(SimbolosColores.RED, yycolumn,yyline,yytext());}
            "Blue"                                   {return new Symbol(SimbolosColores.BLUE, yycolumn,yyline,yytext());}
            "Green"                                  {return new Symbol(SimbolosColores.GREEN, yycolumn,yyline,yytext());}
        "{"                                     {return new Symbol(SimbolosColores.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosColores.LLAVE_C, yycolumn,yyline,yytext());}
        ":"                                     {return new Symbol(SimbolosColores.DOS_PUNTOS, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosColores.COMA, yycolumn,yyline,yytext());}      
        ({Digito}){1,3}                                 {return new Symbol(SimbolosColores.NumRGB, yycolumn,yyline,yytext());}
        ("#")(("A"|"a"|"B"|"b"|"C"|"c"|"D"|"d"|"E"|"e"|"F"|"f"|{Digito}){6,6})       {return new Symbol(SimbolosColores.NumHEX, yycolumn,yyline,yytext());}
        ({Letra}|"_") ({Letra}|"_"|{Digito})*                {return new Symbol(SimbolosColores.Id, yycolumn,yyline,yytext()); }
        [ \t\r\f\n]                            {} 
        .                                            {return new Symbol(SimbolosColores .ERROR,yycolumn,yyline,yytext());}

}
