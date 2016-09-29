(function(){

	angular.module('hub')
		.controller('controller', controller);

	controller.$inject = ['$scope', 'service', 'websocket' ];
	function controller($scope, service, websocket){

		var vm = this;

		websocket.onmessage(function(d){
			vm.mensagemRecebida = d;
			console.log(d);
		});


		vm.mudarTemperaturaAr = function(){

			service.mudarTemperaturaAr(vm.arTemperatura)
					.success(function(e){
						console.log("sucesso ao aumentar temperatura", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});

		};

		vm.desligarAr = function(){
			service.desligarAr()
					.success(function(e){
						console.log("sucesso ao desligar ar", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});
		};


		vm.ligarAr = function(){

			service.ligarAr()
					.success(function(e){
						console.log("sucesso ao ligar ar", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});

		};


		vm.ligarLuz = function(){

			service.ligarLuz()
					.success(function(e){
						console.log("sucesso ao ligar luz", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});

		};


		vm.desligarLuz = function(){

			service.desligarLuz()
					.success(function(e){
						console.log("sucesso ao desligar luz", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});

		};

		vm.mudarBrilhoLuz = function(){

			service.mudarBrilhoLuz(vm.luzBrilho)
					.success(function(e){
						console.log("sucesso ao desligar luz", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});

		};



	}


})();