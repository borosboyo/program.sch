import React from 'react';
import {
    Box,
    chakra,
    Container,
    Stack,
    Text,
    useColorModeValue,
    VisuallyHidden,
} from '@chakra-ui/react';
import {FaGithub, FaInstagram} from 'react-icons/fa';
import {ReactNode} from 'react';

const SocialButton = ({
                          children,
                          label,
                          href,
                      }: {
    children: ReactNode;
    label: string;
    href: string;
}) => {
    return (
        <chakra.button
            bg={useColorModeValue('blackAlpha.100', 'whiteAlpha.100')}
            rounded={'full'}
            w={8}
            h={8}
            cursor={'pointer'}
            as={'a'}
            href={href}
            display={'inline-flex'}
            alignItems={'center'}
            justifyContent={'center'}
            transition={'background 0.3s ease'}
            _hover={{
                bg: useColorModeValue('blackAlpha.200', 'whiteAlpha.200'),
            }}>
            <VisuallyHidden>{label}</VisuallyHidden>
            {children}
        </chakra.button>
    );
};

export function Footer() {

    return (
        <Box
            bg={useColorModeValue('gray.50', 'gray.900')}
            color={useColorModeValue('gray.700', 'gray.200')}>
            <Container
                as={Stack}
                maxW={'3xl'}
                py={3}
                direction={{base: 'column', md: 'row'}}
                spacing={4}
                justify={{base: 'center', md: 'center'}}
                align={{base: 'center', md: 'center'}}>
                <Text>
                    <a className='text-reset fw-bold' href='https://pek.sch.bme.hu/profiles/borosgergo'>
                        &lt;&gt; with ❤ by borosboyo</a>
                </Text>
                <Stack direction={'row'} spacing={6}>
                    <SocialButton label={'Github'} href={'https://github.com/borosboyo'}>
                        <FaGithub/>
                    </SocialButton>
                    <SocialButton label={'Instagram'} href={'https://www.instagram.com/borosboyo/'}>
                        <FaInstagram/>
                    </SocialButton>
                </Stack>
            </Container>
        </Box>
    );
}

export default Footer;
