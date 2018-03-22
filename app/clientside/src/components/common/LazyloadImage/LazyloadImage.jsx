import * as React from 'react';
import PropTypes from 'prop-types';
import './lazyload-image.scss';


class LazyloadImage extends React.PureComponent {

	constructor(props) {
	    super(props);
	    this.handleImageOnLoad = this.handleImageOnLoad.bind(this);
	    this.state = {
	      isImageDisplayed: false,
	    };
	  }


	handleImageOnLoad() {
		this.setState({isImageDisplayed: true});
	}

	render() {

		const backgroundStyle = {
			backgroundColor: this.props.preloadColor,
		};

		const imageClassName = 'lazyload-image-foreground ' + 
			(this.state.isImageDisplayed ? 'show ' : '') + 
			(this.props.zoomIn ? 'zoom-in' : '');

		const imageStyle = {
			backgroundImage: 'url(' + this.props.src + ')',
		};


		return (
			<div className={ ['lazyload-image', this.props.className].join(' ')} style={ backgroundStyle }>
				<img 
					className='lazyload-image-preloader'
					src={ this.props.src }
					onLoad={ this.handleImageOnLoad }
				/>
				<div className={ imageClassName } 
					style={ imageStyle } 
				/>
			</div>
		);
	}
};

LazyloadImage.propTypes = {
	className: PropTypes.string,
  	src: PropTypes.string.isRequired,
	preloadColor: PropTypes.string,
	zoomIn: PropTypes.bool,  
};

LazyloadImage.defaultProps = {
	className: '',
	preloadColor: '#000000',
	zoomIn: false,
};

export { LazyloadImage };