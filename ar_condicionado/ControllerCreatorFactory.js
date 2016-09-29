module.exports = function(target, client){

	var topicName = "arcondicionado";

	var respTopicName = "info/arcondicionado"

	setInterval(function(){

		client.publish( createInfoTopicName("intensidade"), String(target.intensity)  );
		console.log("enviou mensagem");

	}, 1000);


	function createTopicName(subtopic){
		return topicName + "/" + subtopic;
	}

	function createInfoTopicName(subtopic){
		return respTopicName + "/" + subtopic;
	}

	function turnOn(){
		target.on = true;
	}

	function turnDown(){
		target.on = false;
	}

	function getStatus(){
		if(target.on){
			return "ligado";
		}
		else{
			return "desligado";
		}
	}

	function update(v){
		
		if(v > 100 ){
			target.intensity = 100;
		}
		else if(v < 0){
			target.intensity = 0;
		}
		else{
			target.intensity = v;
		}

	}
	
	function getIntensity(){
		return target.intensity;
	}



	return function(cb){

		this[createTopicName("ligar")] = function(){

			console.log("ligar ar condicionado");
			turnOn();

		};

		this[createTopicName("desligar")] = function(){
			turnDown();
			console.log("desligou o ar condicionado");
		};

		this[createTopicName("alterar")] = function(v){

			update(v);
			console.log("alterou temperatura para " + v + " unidades");

		};

		
		this[createTopicName("status")] = function(){
			console.log("status: " + getStatus());
			
			cb(getStatus(), createInfoTopicName("status") );
		};

		this[createTopicName("intensidade")] = function(){
			console.log("intensidade " + getIntensity());
			
			//enviar intensidade para canal tal
			cb( getIntensity(), createInfoTopicName("intensidade")  );
		}

	};
}