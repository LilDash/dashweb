import * as React from 'react';
import PropTypes from 'prop-types';
import { PostCard } from './PostCard';
import './post-list.scss';


class PostList extends React.Component {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
	      //isImageDisplayed: false,
	    };
	  }




	render() {

		
		return (
			<div className='post-list' >
				{ 
					this.props.posts.map((x, i) => 
						<PostCard key={x.id} {...x} />
					)
				}
			</div>
		);
	}
};

PostList.propTypes = {
  	posts: PropTypes.array.isRequired,
  	
};

PostList.defaultProps = {
  	
};

export { PostList };