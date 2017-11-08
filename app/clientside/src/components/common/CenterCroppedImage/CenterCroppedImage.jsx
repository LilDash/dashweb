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
			</div>
		);
	}
};

CenterCroppedImage.defaultProps = {
	isLazyload: false,
  	preloadColor: '#000000',
};

export { CenterCroppedImage };