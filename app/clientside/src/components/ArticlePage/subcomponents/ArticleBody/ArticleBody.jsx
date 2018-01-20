import * as React from 'react';
import './article-body.scss';
import PropTypes from 'prop-types';

export class ArticleBody extends React.Component {
	render() {

		return (
			
			<div className='article-body'>
				<article dangerouslySetInnerHTML={{__html: this.props.content}} />
			</div>
		);
  	}
}

ArticleBody.propTypes = {
	content: PropTypes.string.isRequired,
};

ArticleBody.defaultProps = {
};