(function(){
	
	var ControllerCreatorFactory = require('./ControllerCreatorFactory');
	var mqtt = require('mqtt');
	var client = mqtt.connect('mqtt://broker:1883');

	console.log("iniciou produtor arcondicionado");

	var arcondicionado = {

		on: false,
		intensity: 10

	};

	var ControllerFactory = new ControllerCreatorFactory(arcondicionado);
	
	var controller = new ControllerFactory(function(resp, topic){

		//enviar resposta resp para topico topic
		console.log("enviando resposta '" + resp + "' para topico: '" + topic + "'");

		client.publish(topic, String(resp), {}, function(){
			console.log("enviou resposta '" + resp + "' para topico: '" + topic + "'");
		});


	});


	client.on('connect', function(){

		console.log("conectado ao mqtt");

		client.subscribe('arcondicionado/#');

	});


	client.on('message', function(topic, message){

		controller[topic](message);

		//client.end();

	});


})();

