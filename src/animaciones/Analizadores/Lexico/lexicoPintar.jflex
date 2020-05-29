package animaciones.Analizadores.Lexico;
import java_cup.runtime.Symbol;
import animaciones.Analizadores.Sintactico.SimbolosPintar;
%%
%class AnalizadorLexicoPintar
%cupsym SimbolosPintar
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
        "VARS"                                  {return new Symbol(SimbolosPintar.VARS, yycolumn,yyline,yytext());}
            "INSTRUCCIONES"                          {return new Symbol(SimbolosPintar.INSTRUCCIONES, yycolumn,yyline,yytext());}
            "int"                                  {return new Symbol(SimbolosPintar.INT, yycolumn,yyline,yytext());}
            "String"                                  {return new Symbol(SimbolosPintar.STRING, yycolumn,yyline,yytext());}        
            "boolean"                                   {return new Symbol(SimbolosPintar.BOOLEAN, yycolumn,yyline,yytext());}
            "true"                                  {return new Symbol(SimbolosPintar.TRUE, yycolumn,yyline,yytext());}
"false"                                  {return new Symbol(SimbolosPintar.FALSE, yycolumn,yyline,yytext());}
"PINTAR"                                  {return new Symbol(SimbolosPintar.PINTAR, yycolumn,yyline,yytext());}
"if"                                  {return new Symbol(SimbolosPintar.IF, yycolumn,yyline,yytext());}
"else"                                  {return new Symbol(SimbolosPintar.ELSE, yycolumn,yyline,yytext());}
"while"                                  {return new Symbol(SimbolosPintar.WHILE, yycolumn,yyline,yytext());}
"AND"                                  {return new Symbol(SimbolosPintar.AND, yycolumn,yyline,yytext());}
"OR"                                  {return new Symbol(SimbolosPintar.OR, yycolumn,yyline,yytext());}
        "{"                                     {return new Symbol(SimbolosPintar.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosPintar.LLAVE_C, yycolumn,yyline,yytext());}
        "("                                     {return new Symbol(SimbolosPintar.PARENTESIS_A, yycolumn,yyline,yytext());}
        ")"                                     {return new Symbol(SimbolosPintar.PARENTESIS_C, yycolumn,yyline,yytext());}
        "["                                     {return new Symbol(SimbolosPintar.CORCHETE_A, yycolumn,yyline,yytext());}
        "]"                                     {return new Symbol(SimbolosPintar.CORCHETE_C, yycolumn,yyline,yytext());}
         "="                                     {return new Symbol(SimbolosPintar.IGUAL, yycolumn,yyline,yytext());}
         "=="                                     {return new Symbol(SimbolosPintar.IGUAL_IGUAL, yycolumn,yyline,yytext());}
         "<"                                     {return new Symbol(SimbolosPintar.MENOR, yycolumn,yyline,yytext());}
         ">"                                     {return new Symbol(SimbolosPintar.MAYOR, yycolumn,yyline,yytext());}
         ">="                                     {return new Symbol(SimbolosPintar.MAYOR_IGUAL, yycolumn,yyline,yytext());}
         "<="                                     {return new Symbol(SimbolosPintar.MENOR_IGUAL, yycolumn,yyline,yytext());}
         "<>"                                     {return new Symbol(SimbolosPintar.DIFERENTE, yycolumn,yyline,yytext());}
"+"                                     {return new Symbol(SimbolosPintar.SUMA, yycolumn,yyline,yytext());}
"-"                                     {return new Symbol(SimbolosPintar.RESTA, yycolumn,yyline,yytext());}
"/"                                     {return new Symbol(SimbolosPintar.DIV, yycolumn,yyline,yytext());}
"*"                                     {return new Symbol(SimbolosPintar.MULT, yycolumn,yyline,yytext());}
".."                                     {return new Symbol(SimbolosPintar.PUNTOS_SEGUIDOS, yycolumn,yyline,yytext());}
";"                                     {return new Symbol(SimbolosPintar.PUNTO_COMA, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosPintar.COMA, yycolumn,yyline,yytext());}    
       (("\""|"”"|"“")({Letra}|"_"|{Digito}|{Signo}|" ")+("\""|"”"|"“"))    {return new Symbol(SimbolosPintar.String, yycolumn,yyline,yytext());}
         ({Letra}|"_") ({Letra}|"_"|{Digito})*                {return new Symbol(SimbolosPintar.Id, yycolumn,yyline,yytext()); }
         
        ({Digito})+                                     {return new Symbol(SimbolosPintar.Entero, yycolumn,yyline,yytext());}
        ("//"({Letra}|{Digito}|{Signo}|" "|"\t")+)|("/*"({Letra}|{Digito}|{Signo}|" "|"\t"|"\n"|"\r"|"\f")+"*/")                             {System.out.println("COMENTARIO"+yyline);}
                         [ \t\r\f\n]        {}
        .                                            {System.out.println("ERROR "+yyline);return new Symbol(SimbolosPintar.ERROR,yycolumn,yyline,yytext());}

}


