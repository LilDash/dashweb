import axios from 'axios';

var Ajax = {

	get: function(url, data, callback){
		axios.get(url, data).then(callback).catch(function(error) {
			console.log(error);
		})
	}
};

export { Ajax };