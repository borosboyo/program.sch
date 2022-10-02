import React from 'react';

export default class Footer extends React.Component {
    render() {
        return (
            <footer className='text-center p-2 position-sticky bg-light' id="footer">
                &lt;&gt; <span/>
                with <span/>
                ❤ <span/>
                by <span/>
                <a className='text-reset fw-bold' href='https://pek.sch.bme.hu/profiles/borosgergo'>
                    borosboyo
                </a>
            </footer>
        );
    }
}
