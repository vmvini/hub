(function(){


	angular.module('hub')
		.service('websocket', websocket);

	websocket.$inject = ['$rootScope'];
	function websocket($rootScope){

		var ws = new WebSocket("ws://0.0.0.0:80/middleware/websocket");

		ws.onopen = function(){
			console.log("está conectado");
		};

		ws.onclose = function(){
			console.log("conexao fechada!");
		};

		return {

			onmessage: function(callback){
				ws.onmessage = function(evt){
					$rootScope.$apply(function(){
						callback(evt.data);
					});
				};
			}

		};


	}

})();