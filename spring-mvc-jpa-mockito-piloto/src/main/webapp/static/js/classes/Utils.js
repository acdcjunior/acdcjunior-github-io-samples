/** 
 * Classe: Utils
 * 
 * Dependencias:
 * 	jQuery
 */
Utils = window.Utils || {};

Utils.encontrarNaTela = function (selectorElemento) {
	var $elemento = jQuery(selectorElemento);
	if ($elemento.length === 0) {
		throw Error('Erro ao inicializar componente! Selector '+ selectorElemento + ' nao trouxe nenhum elemento!');
	}
	return $elemento;
};