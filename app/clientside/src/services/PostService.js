import { Ajax } from './utility/Ajax.js';

export function GetHomePagePosts(categoryId, callback){
	Ajax.get('/api/homePagePosts?cid='+categoryId, {}, function(response){
		callback(response.data);
	});
};
