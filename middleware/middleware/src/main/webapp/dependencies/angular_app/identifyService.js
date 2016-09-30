(function(){

	angular.module('hub')
		.service('identify', identify);

	function identify(){

		function forData(data, controller, mapping){

			console.log("dentro de funcao for");

			var split = data.split('::');

			console.log("mapeamento: " + mapping[split[0]]);

			var vmStrAttribute = mapping[split[0]];
			var vmAttWrapper = vmStrAttribute.split('.')[0];
			var vmAttr = vmStrAttribute.split('.')[1];

			if(mapping[split[0]] !== undefined){
				
				(controller[ vmAttWrapper ])[ vmAttr ] = split[1];

				//controller[ mapping[split[0]] ] = split[1];
				console.log("associou");
			}

		}

		return {
			for: forData
		};

	}

})();