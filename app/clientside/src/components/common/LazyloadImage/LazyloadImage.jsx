import * as React from 'react';
import PropTypes from 'prop-types';
import './lazyload-image.scss';


class LazyloadImage extends React.PureComponent {

	constructor(props) {
	    super(props);
	    this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
	      isImageDisplayed: false,
	    };
	  }


	imageOnLoad() {
		this.setState({isImageDisplayed: true});
	}

	render() {

		var backgroundStyle = {
			backgroundColor: this.props.preloadColor,
		};

		var imageStype = {
			backgroundImage: 'url(' + this.props.src + ')',
		};

		return (
			<div className={ ['lazyload-image', this.props.className].join(' ')} style={ backgroundStyle }>
				<img 
					className='lazyload-image-preloader'
					src={ this.props.src }
					onLoad={ this.imageOnLoad }
				/>
				<div className={ this.state.isImageDisplayed ? 'lazyload-image-foreground show' : 'lazyload-image-foreground'} style={ imageStype }></div>
			</div>
		);
	}
};

LazyloadImage.propTypes = {
  	src: PropTypes.string.isRequired,
  	preloadColor: PropTypes.string,
};

LazyloadImage.defaultProps = {
  	preloadColor: '#000000',
};

export { LazyloadImage };