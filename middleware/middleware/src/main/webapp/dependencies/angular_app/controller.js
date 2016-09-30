(function(){

	angular.module('hub')
		.controller('controller', controller);

	controller.$inject = ['$scope', 'service', 'websocket', 'identify' ];
	function controller($scope, service, websocket, identify){

		var vm = this;

		vm.ar = {

			status: "", 
			temperatura: "", 

		};

		vm.luz = {

			status: "", 
			brilho: ""

		};

		vm.novoArTemperatura = "";
		vm.novoBrilho = "";
		
		websocket.onmessage(function(d){
			//vm.mensagemRecebida = d;

			identify.for(d, vm, {

				"info/arcondicionado/status": "ar.status",
				"info/arcondicionado/intensidade":"ar.temperatura", 
				"info/lampada/status":"luz.status", 
				"info/lampada/intensidade":"luz.brilho"


			});

			console.log(d);
		});


		vm.mudarTemperaturaAr = function(){

			service.mudarTemperaturaAr(vm.novoArTemperatura)
					.success(function(e){
						console.log("sucesso ao mudar temperatura", e);
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

			service.mudarBrilhoLuz(vm.novoBrilho)
					.success(function(e){
						console.log("sucesso ao desligar luz", e);
					})	
					.error(function(e){
						console.log("erro: ", e);
					});

		};



	}


})();