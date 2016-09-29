(function(){

	angular.module('hub')
		.service('service', service);

	service.$inject = ['$http'];
	function service($http){

		function mudarTemperaturaAr(v){
			return $http.put('/api/arcondicionado/alterar/'+v);
		}

		function desligarAr(){
			return $http.put('/api/arcondicionado/desligar');
		}

		function ligarAr(){
			return $http.put('/api/arcondicionado/ligar');
		}

		function ligarLuz(){
			return $http.put('/api/lampada/ligar');
		}

		function desligarLuz(){
			return $http.put('/api/lampada/desligar');
		}

		function mudarBrilhoLuz(v){
			return $http.put('/api/lampada/alterar/' + v);
		}


		return {
			mudarTemperaturaAr: mudarTemperaturaAr,
			desligarAr: desligarAr, 
			ligarAr: ligarAr, 
			ligarLuz: ligarLuz, 
			desligarLuz: desligarLuz, 
			mudarBrilhoLuz: mudarBrilhoLuz
		};

	}

})();