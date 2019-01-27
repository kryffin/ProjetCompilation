package compilateurYal.analyse ;

import java_cup.runtime.*;
import compilateurYal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+
guillemet = [\"]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaire = [/][/].*

%%

"programme"             { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                 { return symbol(CodesLexicaux.DEBUT); }
"fin"              	    { return symbol(CodesLexicaux.FIN); }

"ecrire"                { return symbol(CodesLexicaux.ECRIRE); }

"lire"                  { return symbol(CodesLexicaux.LIRE); }

"entier"                { return symbol(CodesLexicaux.ENTIER); }

";"                     { return symbol(CodesLexicaux.POINTVIRGULE); }

"+"                     { return symbol(CodesLexicaux.PLUS); }

"-"                     { return symbol(CodesLexicaux.MOINS); }

"*"                     { return symbol(CodesLexicaux.FOIS); }

"/"                     { return symbol(CodesLexicaux.DIV); }

"("                     { return symbol(CodesLexicaux.PAR_OUV); }

")"                     { return symbol(CodesLexicaux.PAR_FER); }

"["                     { return symbol(CodesLexicaux.CRO_OUV); }

"]"                     { return symbol(CodesLexicaux.CRO_FER); }

"="                     { return symbol(CodesLexicaux.EGAL); }

{csteE}      	        { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	            { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}                { }

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

{commentaire}           {}

