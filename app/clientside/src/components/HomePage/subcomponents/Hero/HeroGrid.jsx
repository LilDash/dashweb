import * as React from 'react';
import './hero-grid.scss';
import PropTypes from 'prop-types';
import { CityCard } from '../../../common/CityCard/CityCard';


export class HeroGrid extends React.Component {
	render() {
		return (
			<div className='hero-grid'>
				<div className='hero-grid-l' />
                <div className='hero-grid-r'>
                    <div className='hero-grid-city' >
                        <CityCard />
                        <p className='hero-grid-city-name'>圣彼得堡</p>
                    </div>
                </div>
				
			</div>
		);
  	}
}

HeroGrid.propTypes = {
	
};

HeroGrid.defaultProps = {
};