define(['jQuery', 'classes/Instituicao'], function() {
	
	describe("Testes do Componente Instituicao", function() {
		
		describe("Classe Instituicao", function() {
		
		    beforeEach(function () {
				componenteInstituicaoGlobal = {};
				componenteInstituicaoGlobal.adicionarAoCache = jasmine.createSpy("adicionarAoCache() spy");
		    });
		    
			describe("new Instituicao() - Construtor", function() {
		
			    it("Se o nome for unico, deve adicionar ao cache com o nome como chave", function() {
					// setup
			    	var obj = {id:11201,cnpj:'',nome:'NOMEUNICO',cnpjUnico:0,nomeUnico:1,consorciadas:[]};
					// execute
					var instituicao = new Instituicao(obj);
					// verify
					expect(componenteInstituicaoGlobal.adicionarAoCache).toHaveBeenCalledWith('NOMEUNICO', instituicao);
					expect(componenteInstituicaoGlobal.adicionarAoCache.callCount).toBe(1);
				});
			  
				it("Se o nome nao for unico, nao deve adicionar ao cache", function() {
					// setup
					var obj = {id:11201,cnpj:'',nome:'NOME NAO UNICO',cnpjUnico:0,nomeUnico:0,consorciadas:[]};
					// execute
					new Instituicao(obj);
					// verify
					expect(componenteInstituicaoGlobal.adicionarAoCache).not.toHaveBeenCalled();
				});
				
				it("Se o CNPJ for unico, mas for vazio, nao deve entrar", function() {
					// setup
					var obj = {id:11201,cnpj:'',nome:'NOME NAO UNICO',cnpjUnico:1,nomeUnico:0,consorciadas:[]};
					// execute
					new Instituicao(obj);
					// verify
					expect(componenteInstituicaoGlobal.adicionarAoCache).not.toHaveBeenCalled();
				});
				
				it("Se o CNPJ for unico, deve entrar no cache como chave, mesmo que seja mal formado e nao vazio", function() {
					// setup
					var obj = {id:11201,cnpj:'1234',nome:'NOME NAO UNICO',cnpjUnico:1,nomeUnico:0,consorciadas:[]};
					// execute
					var instituicao = new Instituicao(obj);
					// verify
					expect(componenteInstituicaoGlobal.adicionarAoCache).toHaveBeenCalledWith('1234', instituicao);
					expect(componenteInstituicaoGlobal.adicionarAoCache).toHaveBeenCalledWith('1234 - NOME NAO UNICO', instituicao);
					expect(componenteInstituicaoGlobal.adicionarAoCache.callCount).toBe(2);
				});
				
				it("Se o CNPJ for unico, mas for vazio, nao deve entrar", function() {
					// setup
					var obj = {id:11201,cnpj:'',nome:'NOME NAO UNICO',cnpjUnico:1,nomeUnico:0,consorciadas:[]};
					// execute
					new Instituicao(obj);
					// verify
					expect(componenteInstituicaoGlobal.adicionarAoCache).not.toHaveBeenCalled();
				});
				
				it("Se o CNPJ estiver preenchido, mesmo se ninguem for unico, deve entrar como CNPJ-NOME", function() {
					/* EXPLICACAO: Este caso eh apoiado pelas instituicoes ja na base. Todas que têm o 
					 * CNPJ preenchido e duplicado sao unicas quando juntamos o par "CNPJ - NOME".
					 * Como novas empresas com CNPJs existentes nao poderao ser adicionadas, esta checagem serah
					 * valida para sempre. 
					 */
					// setup
					var obj = {id:11201,cnpj:'1234',nome:'NOME NAO UNICO',cnpjUnico:0,nomeUnico:0,consorciadas:[]};
					// execute
					var instituicao = new Instituicao(obj);
					// verify
					expect(componenteInstituicaoGlobal.adicionarAoCache).toHaveBeenCalledWith('1234 - NOME NAO UNICO', instituicao);
					expect(componenteInstituicaoGlobal.adicionarAoCache.callCount).toBe(1);
				});
			
			});
			
			it("toJson() deve gerar instituicao nao-consorcio em formato json", function() {
				// setup
				var obj = {id:11201,cnpj:'1234',nome:'NOME QUALQUER',cnpjUnico:1,nomeUnico:0,consorciadas:[]};
				var jsonEsperado = '{"id":11201,"cnpj":"1234","nome":"NOME QUALQUER","cnpjUnico":1,"nomeUnico":0,"consorciadas":[]}';
				// execute
				var instituicao = new Instituicao(obj);
				var jsonGerado = instituicao.toJson();
				// verify
				expect(jsonGerado).toEqual(jsonEsperado);
			});
			
			it("toJson() deve gerar consorcio em formato json", function() {
				// setup
				var obj = {id:11201,cnpj:'1234',nome:'NOME QUALQUER',cnpjUnico:1,nomeUnico:0,consorciadas:["ia","ib"]};
				var jsonEsperado = '{"id":11201,"cnpj":"1234","nome":"NOME QUALQUER","cnpjUnico":1,"nomeUnico":0,"consorciadas":["ia","ib"]}';
				// execute
				var instituicao = new Instituicao(obj);
				var jsonGerado = instituicao.toJson();
				// verify
				expect(jsonGerado).toEqual(jsonEsperado);
			});
			
			it("getCnpjNome() deve trazer somente o nome se o cnpj estiver em branco", function() {
				// setup
				var obj = {id:11201,cnpj:'',nome:'NOME QUALQUER',cnpjUnico:0,nomeUnico:0,consorciadas:[]};
				// execute
				var instituicao = new Instituicao(obj);
				var cnpjNome = instituicao.getCnpjNome();
				// verify
				expect(cnpjNome).toEqual('NOME QUALQUER');
			});
			
			it("getCnpjNome() deve trazer 'cnpj - nome' se o cnpj NAO estiver em branco", function() {
				// setup
				var obj = {id:11201,cnpj:'12345678901234',nome:'NOME QUALQUER',cnpjUnico:0,nomeUnico:0,consorciadas:[]};
				// execute
				var instituicao = new Instituicao(obj);
				var cnpjNome = instituicao.getCnpjNome();
				// verify
				expect(cnpjNome).toEqual('12345678901234 - NOME QUALQUER');
			});
		
		});
		
	});
});