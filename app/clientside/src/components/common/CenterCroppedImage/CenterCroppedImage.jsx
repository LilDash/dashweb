import * as React from 'react';
import PropTypes from 'prop-types';
import { LazyloadImage } from '../LazyloadImage/LazyloadImage';
import './center-cropped-image.scss';


class CenterCroppedImage extends React.PureComponent {

	constructor(props) {
	    super(props);
	}

	render() {
		var image;
		if (this.props.isLazyload){
			image = <LazyloadImage {...this.props} className='image' />;
		} else {
			image = <img {...this.props} />;
		}

		return (
			<div className={[this.props.className, 'center-cropped-image',].join(' ') } >
				{ image }
				{ this.props.withShadow ? 
					<div className={`center-cropped-image-shadow ${this.props.shadowDarker ? "darker" : ""}`} /> : null 
				}
			</div>
		);
	}
};

CenterCroppedImage.propTypes = {
	className: PropTypes.string,
	isLazyload: PropTypes.bool,
	preloadColor: PropTypes.string,
	withShadow: PropTypes.bool,
	shadowDarker: PropTypes.bool,
	zoomIn: PropTypes.bool,
};

CenterCroppedImage.defaultProps = {
	className: '',
	isLazyload: false,
	preloadColor: 'rgba(0,0,0,0)',
	withShadow: false,
	shadowDarker: false, 
	zoomIn: false,
};

export { CenterCroppedImage };