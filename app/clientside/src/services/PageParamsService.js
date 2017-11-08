
export function GetPageParams(){
	if (pageConfig){
		switch (pageConfig.pageType){
			case 'home': return homePageParams;
			default: return {};
		}
	}

	return {};
};
