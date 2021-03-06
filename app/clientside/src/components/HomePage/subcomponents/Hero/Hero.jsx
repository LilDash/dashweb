import * as React from 'react';
import './hero.scss';
import PropTypes from 'prop-types';

import { LazyloadImage } from '../../../common/LazyloadImage/LazyloadImage';
import { HeroGrid } from './HeroGrid';

export class Hero extends React.Component {
	render() {
		return (
			<div className='hero'>
				<LazyloadImage 
					className='hero-background' 
					src={this.props.imageSrc} 
					displayLoading={false} 
					preloadColor={this.props.preloadColor} 
				/>
				<HeroGrid {...this.props.heroGrid} />
			</div>
		);
  	}
}

Hero.propTypes = {
	imageSrc: PropTypes.string.isRequired,
	preloadColor: PropTypes.string.isRequired,
	heroGrid: PropTypes.object.isRequired,
};

Hero.defaultProps = {
};