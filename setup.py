from setuptools import setup, find_packages

setup(
    name="jasypt-python",
    version="1.0.0",
    packages=find_packages(),
    package_data={"jasypt_python": ["jasypt-wrapper.jar"]},
    install_requires=[],
)
