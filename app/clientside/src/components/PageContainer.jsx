import * as React from 'react';
import PropTypes from 'prop-types';

import HomePage from './HomePage/HomePage';
import ArticlePage from './ArticlePage/ArticlePage';

export default class PageContainer extends React.Component {

	renderPageComponent() {
		switch (this.props.pageType) {
			case 'Home': return (<HomePage {...this.props.pageParams} />);
			case 'Article': return (<ArticlePage {...this.props.pageParams} />);
			default: return null;
		}
	}

	render() {
		return (
			<div className="page-container">
				{this.renderPageComponent()}
			</div>
		);
	}
};

PageContainer.propTypes = {
  pageType: PropTypes.string.isRequired,
  pageParams: PropTypes.object,
};

PageContainer.defaultProps = {
  pageParams: {},
};