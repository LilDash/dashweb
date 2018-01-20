import * as React from 'react';
import { HashRouter, Route } from 'react-router-dom';
import PropTypes from 'prop-types';

import HomePage from './HomePage/HomePage';
import ArticlePage from './ArticlePage/ArticlePage';



export default class PageRouter extends React.Component {

	render() {
		return (
			<HashRouter>
				<div>
				<Route exact path="/" render={(props) => ( <HomePage {...this.props.pageParams} /> )} />
				<Route path="/a/:id" component={ArticlePage} />
				</div>
			</HashRouter>
		);
	}
};


PageRouter.propTypes = {
  pageParams: PropTypes.object,
};

PageRouter.defaultProps = {
  pageParams: {},
};
