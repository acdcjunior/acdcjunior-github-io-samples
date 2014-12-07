define([ 'jQuery', 'classes/Utils' ], function() {

	describe("Classe Utils.js", function() {

		beforeEach(function() {
			setFixtures('<div id="teste" class="elementoExiste">');
		});

		it("encontrarNaTela() deve encontrar o elemento se ele existir", function() {
			var element = Utils.encontrarNaTela('#teste');
			
			expect(element).toHaveClass('elementoExiste');
		});
		
		it("encontrarNaTela() deve lancar erro se o elemento nao existe", function() {
			
			expect(function() {
				Utils.encontrarNaTela('#elementoQueNaoExiste');
			}).toThrow();
		});

	});
	
});