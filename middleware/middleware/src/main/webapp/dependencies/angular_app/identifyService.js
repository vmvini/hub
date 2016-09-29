(function(){

	angular.module('hub')
		.service('identify', identify);

	function identify(){

		function forData(data, controller, mapping){

			console.log("dentro de funcao for");

			var split = data.split('::');

			console.log("mapeamento: " + mapping[split[0]]);

			if(mapping[split[0]] !== undefined){
				controller[ mapping[split[0]] ] = split[1];
				console.log("associou");
			}

		}

		return {
			for: forData
		};

	}

})();