import * as React from 'react';
import PropTypes from 'prop-types';
import './highlight-nav.scss';

import { HighlightNavItem } from './HighlightNavItem';


class HighlightNav extends React.Component {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
	      //isImageDisplayed: false,
	    };
	  }




	render() {

		
		return (
			<div className='highlight-nav' >
				<HighlightNavItem {...this.props.blog} />
				<HighlightNavItem {...this.props.travel} />
				<HighlightNavItem {...this.props.shopping} />
			</div>
		);
	}
};

HighlightNav.propTypes = {
  	blog: PropTypes.object.isRequired,
  	travel: PropTypes.object.isRequired,
  	shopping: PropTypes.object.isRequired,
};

HighlightNav.defaultProps = {
  	
};

export { HighlightNav };