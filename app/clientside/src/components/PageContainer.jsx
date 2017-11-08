import * as React from 'react';
import PropTypes from 'prop-types';
import 'reset-css/reset.css';

import HomePage from './HomePage/HomePage.jsx';

export default class PageContainer extends React.Component {

	renderPageComponent() {
		switch (this.props.pageType) {
			case 'home': return (<HomePage {...this.props.pageParams} />);
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