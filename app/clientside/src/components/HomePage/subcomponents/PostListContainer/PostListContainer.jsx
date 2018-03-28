import * as React from 'react';
import './post-list-container.scss';
import PropTypes from 'prop-types';

import { PostList } from '../PostList/PostList';
import { SectionBracket } from '../SectionBracket/SectionBracket';

export class PostListContainer extends React.Component {

    constructor(props) {
	    super(props);
	    this.state = {
	    
	    };
      }
      
	render() {
		return (
			<div className='post-list-container'>
				<SectionBracket name={this.props.category} direction='down' />
                <PostList posts={this.props.posts} />
			</div>
		);
  	}
}

PostListContainer.propTypes = {
    category: PropTypes.string.isRequired,
    posts: PropTypes.array.isRequired,
};

PostListContainer.defaultProps = {
};