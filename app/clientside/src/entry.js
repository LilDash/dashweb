import React from 'react';
import ReactDOM from 'react-dom';
import PageContainer from './components/PageContainer.jsx';
import { GetPageParams } from './services/PageParamsService.js';

main();

function main() {
    ReactDOM.render(<PageContainer pageType={pageConfig.pageType} pageParams={GetPageParams()} />, document.getElementById('app'));
}