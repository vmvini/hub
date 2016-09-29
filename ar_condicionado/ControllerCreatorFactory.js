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

	function increase(v){
		
		if(target.intensity + Number(v) > 100 ){
			target.intensity = 100;
		}
		else{ 
			target.intensity += Number(v);
		}

	}

	function decrease(v){
		
		if(target.intensity - Number(v) < 0){
			target.intensity = 0;
		}
		else{
			target.intensity -= Number(v);
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