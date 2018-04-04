import { Ajax } from './utility/Ajax.js';

export function GetHomePagePosts(categoryId, page, callback){
	Ajax.get('/api/homePagePosts?cid='+categoryId+'&p='+page, {}, function(response){
		callback(response.data);
	});
};
