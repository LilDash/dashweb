
export function GetPageParams(){
	if (pageConfig){
		switch (pageConfig.pageType){
			case 'Home': return homePageParams;
			case 'Article': return articlePageParams;
			default: return {};
		}
	}

	return {};
};
