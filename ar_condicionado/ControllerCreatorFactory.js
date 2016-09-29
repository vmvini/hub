module.exports = function(target){

	var topicName = "arcondicionado";

	var respTopicName = "info/arcondicionado"

	function createTopicName(subtopic){
		return topicName + "/" + subtopic;
	}

	function createInfoTopicName(subtopic){
		return respTopicName + "/" + subtopic;
	}

	function turnOn(){
		target.on = true;
		target.off = false;
	}

	function turnDown(){
		target.on = false;
		target.off = true;
	}

	function getStatus(){
		return {

			on: target.on,
			off: target.off

		};
	}

	function increase(v){
		target.intensity += v;
	}

	function decrease(v){
		target.intensity -= v;
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

		this[createTopicName("aumentar")] = function(v){

			increase(v);
			console.log("aumentou temperatura em " + v + " unidades");

		};

		this[createTopicName("diminuir")] = function(v){
			decrease(v);
			console.log("diminuiu temperatura em " + v + " unidades");
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