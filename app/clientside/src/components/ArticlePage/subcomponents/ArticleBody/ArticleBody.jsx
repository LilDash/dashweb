import * as React from 'react';
import ReactHtmlParser, { processNodes, convertNodeToElement, htmlparser2 } from 'react-html-parser';

import './article-body.scss';
import PropTypes from 'prop-types';

export class ArticleBody extends React.Component {
	render() {

		return (
			
			<div className='article-body'>
				<article dangerouslySetInnerHTML={{__html: ReactHtmlParser(this.props.content, {decodeEntities:true })}} />
			</div>
		);
  	}
}

ArticleBody.propTypes = {
	content: PropTypes.string.isRequired,
};

ArticleBody.defaultProps = {
};