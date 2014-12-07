/* Define a variavel "console" para os testes que rodam no HtmlUnit nao falharem */
if (typeof window.console == "undefined") {
	window.console = {
		log : function() { },
		error : function() { },
		debug : function() { },
		dir : function() { }
	};
}