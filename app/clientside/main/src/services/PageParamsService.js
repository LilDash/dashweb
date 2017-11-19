
export function GetPageParams(){
	if (pageConfig){
		switch (pageConfig.pageType){
			case 'Home': return homePageParams;
			default: return {};
		}
	}

	return {};
};
