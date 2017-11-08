import * as React from 'react';
import PropTypes from 'prop-types';
import { CenterCroppedImage } from '../../../common/CenterCroppedImage/CenterCroppedImage';


class HighlightNavItem extends React.PureComponent {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
	      //isImageDisplayed: false,
	    };
	  }




	render() {

		
		return (
			<div className='highlight-nav-item' >
				<CenterCroppedImage 
					className='highlight-nav-item-img' 
					src={ this.props.imageSrc }
					isLazyload={ true }
					preloadColor={ this.props.imagePreloadColor }

				/>
				<div className='highlight-nav-item-text'>
					<span className='highlight-nav-item-title'>{ this.props.title }</span>
					<p className='highlight-nav-item-desc'>{ this.props.description }</p>
				</div>

			</div>
		);
	}
};

HighlightNavItem.propTypes = {
  	imageSrc: PropTypes.string.isRequired,
  	title: PropTypes.string.isRequired,
  	description: PropTypes.string.isRequired,
  	href: PropTypes.string.isRequired,
  	imagePreloadColor: PropTypes.string,
};

HighlightNavItem.defaultProps = {
  	
};

export { HighlightNavItem };