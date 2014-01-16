/*
 * CLASSE: Instituicao
 * 
 * Dependencias:
 * 	ComponenteInstituicaoGlobal.js
 */
function Instituicao(jsonInstituicao) {
	if (jsonInstituicao == null				 || jsonInstituicao.id == null			 || jsonInstituicao.cnpj == null		 ||
		jsonInstituicao.nome == null		 || jsonInstituicao.cnpjUnico == null	 || jsonInstituicao.nomeUnico == null	 ||
		jsonInstituicao.consorciadas == null) {
		throw new Error('Objeto enviado para geracao de instituicao eh invalido: '+jsonInstituicao);
	}
	this.id = jsonInstituicao.id;
	this.cnpj = jsonInstituicao.cnpj;
	this.nome = jsonInstituicao.nome;
	this.cnpjUnico = jsonInstituicao.cnpjUnico;
	this.nomeUnico = jsonInstituicao.nomeUnico;
	this.consorciadas = jsonInstituicao.consorciadas;
	this.adicionarAoCache();
}
Instituicao.prototype = {
	id : null,
	cnpj : null,
	nome : null,
	cnpjUnico : null,
	nomeUnico : null,
	consorciadas : [], /* serah consorcio se tiver algum elemento neste array */
	
	/**
	 * Adiciona o instituicao em questao ao cache global, se seu nome/cnpj forem unicos.
	 */
	adicionarAoCache : function() {
		if (this.nomeUnico) {
			componenteInstituicaoGlobal.adicionarAoCache(this.nome, this);
		}
		var cnpjPreenchido = this.cnpj.length > 0;
		if (cnpjPreenchido && this.cnpjUnico) {
			componenteInstituicaoGlobal.adicionarAoCache(this.cnpj, this);
		}
		if (cnpjPreenchido) {
			componenteInstituicaoGlobal.adicionarAoCache(this.getCnpjNome(), this);
		}
	},
	
	getCnpjNome : function () {
		return this.cnpj + (this.cnpj.length > 0 ? " - " : "") + this.nome;
	},
	
	toJson : function () {
		var jsonConsorciadas = '[]';
		if (this.consorciadas.length > 0) {
			jsonConsorciadas = '["' + this.consorciadas.join('","') + '"]';
		}
		return '{"id":'+this.id+','+
		       '"cnpj":"'+this.cnpj+'",'+
		       '"nome":"'+this.nome+'",'+
		       '"cnpjUnico":'+this.cnpjUnico+','+
		       '"nomeUnico":'+this.nomeUnico+','+
		       '"consorciadas":'+jsonConsorciadas+'}';
	}

};