import * as React from 'react';
import './article-hero.scss';
import PropTypes from 'prop-types';

import { LazyloadImage } from '../../../common/LazyloadImage/LazyloadImage';

export class ArticleHero extends React.Component {
	render() {
		return (
			<div className='article-hero'>
				<LazyloadImage src={this.props.titleImageSrc} preloadColor={this.props.preloadColor} />
				<div className='article-hero-overlay' />
				<div className='article-hero-inner'>
					<h1 className='article-title'>{this.props.title}</h1>
					<div className='article-meta'>
						<span className='article-meta-category'>{this.props.categoryName}</span>
						<span className='article-meta-time'>{this.props.publishTime}</span>
						<span className='article-meta-author'>{this.props.authorName}</span>
					</div>
				</div>
				
			</div>
		);
  	}
}

ArticleHero.propTypes = {
	titleImageSrc: PropTypes.string.isRequired,
	preloadColor: PropTypes.string.isRequired,
	title: PropTypes.string.isRequired,
	categoryName: PropTypes.string.isRequired,
	publishTime: PropTypes.string.isRequired,
	authorName: PropTypes.string.isRequired,
};

ArticleHero.defaultProps = {
};