import * as React from 'react';
import PropTypes from 'prop-types';
import './post-card.scss';
import { CenterCroppedImage } from '../../../common/CenterCroppedImage/CenterCroppedImage';

class PostCard extends React.PureComponent {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
	      //isImageDisplayed: false,
	    };
	  }




	render() {

		
		return (
			<div className='post-card' data-id={this.props.id}>
				<div className='post-card-img-box'>
					<CenterCroppedImage 
						className='post-card-img' 
						src={ this.props.imageSrc }
						isLazyload={ true }
						preloadColor='#009966'
					/>
					<span className='post-card-mark'>{ this.props.mark }</span>
				</div>
				<div className='post-card-text-box'>
					<span className='post-card-title'>{ this.props.title }</span>
					<div className='post-card-summary'>{ this.props.summary }</div>
					
				</div>
				<div className='post-card-info-box'>
					<span className='post-card-info-time'>{ this.props.time }</span>
					<div className='post-card-info-tags'>
						{ this.props.tags.map((x, i) => 
							<span key={i}>{ x }  </span>
						) }
					</div>
					<div className='post-card-info-comments'>{ this.props.comments }个评论</div>
				</div>
			</div>
		);
	}
};

PostCard.propTypes = {
	id: PropTypes.number.isRequired,
  	imageSrc: PropTypes.string.isRequired,
  	mark: PropTypes.string.isRequired,
  	title: PropTypes.string.isRequired,
  	summary: PropTypes.string.isRequired,
  	time: PropTypes.string.isRequired,
  	tags: PropTypes.array.isRequired,
  	comments: PropTypes.number.isRequired,
};

PostCard.defaultProps = {
  	
};

export { PostCard };