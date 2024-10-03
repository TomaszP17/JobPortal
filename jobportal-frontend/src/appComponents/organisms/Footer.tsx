import { Button } from '../atoms/Button'
import { InputField } from '../atoms/InputField'

export function Footer() {
    return (
        <footer className="bg-gray-100">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                <div className="space-y-4">
                    <h3 className="text-lg font-semibold text-gray-900">About Us</h3>
                    <p className="text-sm text-gray-600">
                        We are a leading job board platform connecting talented professionals with exciting career opportunities.
                    </p>
                </div>

                <div className="space-y-4">
                    <h3 className="text-lg font-semibold text-gray-900">Quick Links</h3>
                    <ul className="space-y-2">
                        <li>
                            <a href="/jobs" className="text-sm text-gray-600 hover:text-gray-900">
                                Find Jobs
                            </a>
                        </li>
                        <li>
                            <a href="/companies" className="text-sm text-gray-600 hover:text-gray-900">
                                Companies
                            </a>
                        </li>
                        <li>
                            <a href="/resources" className="text-sm text-gray-600 hover:text-gray-900">
                                Resources
                            </a>
                        </li>
                        <li>
                            <a href="/contact" className="text-sm text-gray-600 hover:text-gray-900">
                                Contact Us
                            </a>
                        </li>
                    </ul>
                </div>

                <div className="space-y-4">
                    <h3 className="text-lg font-semibold text-gray-900">Follow Us</h3>
                    <div className="flex space-x-4">
                        <a href="https://twitter.com" target="_blank" rel="noopener noreferrer" className="text-gray-600 hover:text-gray-900">
                            <span className="sr-only">Twitter</span>
                            <svg className="h-6 w-6" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true">
                                <path d="M8.29 20.251c7.547 0 11.675-6.253 11.675-11.675 0-.178 0-.355-.012-.53A8.348 8.348 0 0022 5.92a8.19 8.19 0 01-2.357.646 4.118 4.118 0 001.804-2.27 8.224 8.224 0 01-2.605.996 4.107 4.107 0 00-6.993 3.743 11.65 11.65 0 01-8.457-4.287 4.106 4.106 0 001.27 5.477A4.072 4.072 0 012.8 9.713v.052a4.105 4.105 0 003.292 4.022 4.095 4.095 0 01-1.853.07 4.108 4.108 0 003.834 2.85A8.233 8.233 0 012 18.407a11.616 11.616 0 006.29 1.84" />
                            </svg>
                        </a>
                        <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer" className="text-gray-600 hover:text-gray-900">
                            <span className="sr-only">LinkedIn</span>
                            <svg className="h-6 w-6" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true">
                                <path d="M20.447 20.452h-3.554v-5.569c0-1.328-.027-3.037-1.852-3.037-1.853 0-2.136 1.445-2.136 2.939v5.667H9.351V9h3.414v1.561h.046c.477-.9 1.637-1.85 3.37-1.85 3.601 0 4.267 2.37 4.267 5.455v6.286zM5.337 7.433c-1.144 0-2.063-.926-2.063-2.065 0-1.138.92-2.063 2.063-2.063 1.14 0 2.064.925 2.064 2.063 0 1.139-.925 2.065-2.064 2.065zm1.782 13.019H3.555V9h3.564v11.452zM22.225 0H1.771C.792 0 0 .774 0 1.729v20.542C0 23.227.792 24 1.771 24h20.451C23.2 24 24 23.227 24 22.271V1.729C24 .774 23.2 0 22.222 0h.003z" />
                            </svg>
                        </a>
                    </div>
                </div>

                <div className="space-y-4">
                    <h3 className="text-lg font-semibold text-gray-900">Newsletter</h3>
                    <p className="text-sm text-gray-600">Subscribe to our newsletter for the latest job updates.</p>
                    {/*<form className="space-y-2">*/}
                    {/*    <InputField*/}
                    {/*        type="email"*/}
                    {/*        placeholder="Enter your email"*/}
                    {/*    />*/}
                    {/*    <Button>Subscribe</Button>*/}
                    {/*</form>*/}
                </div>
            </div>
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4 border-t border-gray-200">
                <p className="text-sm text-center text-gray-500">
                    © {new Date().getFullYear()} JobPortal. All rights reserved.
                </p>
            </div>
        </footer>
    )
}