import { Ajax } from './utility/Ajax.js';

export function GetHomePagePosts(callback){
	Ajax.get('/api/homePagePosts', {}, function(response){
		callback(response.data);
	});
};
