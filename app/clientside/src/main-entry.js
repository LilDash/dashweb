import React from 'react';
import ReactDOM from 'react-dom';
import PageContainer from './components/PageContainer.jsx';
import { GetPageParams } from './services/PageParamsService.js';

//import PageRouter from './components/PageRouter.jsx';

main();

function main() {
    ReactDOM.render(<PageContainer pageType={pageConfig.pageType} pageParams={GetPageParams()} />, 
    	document.getElementById('app'));
    //ReactDOM.render(<PageRouter pageParams={GetPageParams()} />, document.getElementById('app'));
}

