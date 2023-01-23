//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

public class JPEGDecoder
{
    static final int DCTSIZE = 8;
    static final int DCTSIZE2 = 64;
    static final int NUM_QUANT_TBLS = 4;
    static final int NUM_HUFF_TBLS = 4;
    static final int NUM_ARITH_TBLS = 16;
    static final int MAX_COMPS_IN_SCAN = 4;
    static final int MAX_COMPONENTS = 10;
    static final int MAX_SAMP_FACTOR = 4;
    static final int D_MAX_BLOCKS_IN_MCU = 10;
    static final int HUFF_LOOKAHEAD = 8;
    static final int MAX_Q_COMPS = 4;
    static final int IFAST_SCALE_BITS = 2;
    static final int MAXJSAMPLE = 255;
    static final int CENTERJSAMPLE = 128;
    static final int MIN_GET_BITS = 25;
    static final int INPUT_BUFFER_SIZE = 4096;
    static final int SCALEBITS = 16;
    static final int ONE_HALF = 32768;
    static final int RGB_RED = 2;
    static final int RGB_GREEN = 1;
    static final int RGB_BLUE = 0;
    static final int RGB_PIXELSIZE = 3;
    static final int JBUF_PASS_THRU = 0;
    static final int JBUF_SAVE_SOURCE = 1;
    static final int JBUF_CRANK_DEST = 2;
    static final int JBUF_SAVE_AND_PASS = 3;
    static final int JPEG_MAX_DIMENSION = 65500;
    static final int BITS_IN_JSAMPLE = 8;
    static final int JDITHER_NONE = 0;
    static final int JDITHER_ORDERED = 1;
    static final int JDITHER_FS = 2;
    static final int JDCT_ISLOW = 0;
    static final int JDCT_IFAST = 1;
    static final int JDCT_FLOAT = 2;
    static final int JDCT_DEFAULT = 0;
    static final int JCS_UNKNOWN = 0;
    static final int JCS_GRAYSCALE = 1;
    static final int JCS_RGB = 2;
    static final int JCS_YCbCr = 3;
    static final int JCS_CMYK = 4;
    static final int JCS_YCCK = 5;
    static final int SAVED_COEFS = 6;
    static final int Q01_POS = 1;
    static final int Q10_POS = 8;
    static final int Q20_POS = 16;
    static final int Q11_POS = 9;
    static final int Q02_POS = 2;
    static final int CTX_PREPARE_FOR_IMCU = 0;
    static final int CTX_PROCESS_IMCU = 1;
    static final int CTX_POSTPONED_ROW = 2;
    static final int APP0_DATA_LEN = 14;
    static final int APP14_DATA_LEN = 12;
    static final int APPN_DATA_LEN = 14;
    static final int M_SOF0 = 192;
    static final int M_SOF1 = 193;
    static final int M_SOF2 = 194;
    static final int M_SOF3 = 195;
    static final int M_SOF5 = 197;
    static final int M_SOF6 = 198;
    static final int M_SOF7 = 199;
    static final int M_JPG = 200;
    static final int M_SOF9 = 201;
    static final int M_SOF10 = 202;
    static final int M_SOF11 = 203;
    static final int M_SOF13 = 205;
    static final int M_SOF14 = 206;
    static final int M_SOF15 = 207;
    static final int M_DHT = 196;
    static final int M_DAC = 204;
    static final int M_RST0 = 208;
    static final int M_RST1 = 209;
    static final int M_RST2 = 210;
    static final int M_RST3 = 211;
    static final int M_RST4 = 212;
    static final int M_RST5 = 213;
    static final int M_RST6 = 214;
    static final int M_RST7 = 215;
    static final int M_SOI = 216;
    static final int M_EOI = 217;
    static final int M_SOS = 218;
    static final int M_DQT = 219;
    static final int M_DNL = 220;
    static final int M_DRI = 221;
    static final int M_DHP = 222;
    static final int M_EXP = 223;
    static final int M_APP0 = 224;
    static final int M_APP1 = 225;
    static final int M_APP2 = 226;
    static final int M_APP3 = 227;
    static final int M_APP4 = 228;
    static final int M_APP5 = 229;
    static final int M_APP6 = 230;
    static final int M_APP7 = 231;
    static final int M_APP8 = 232;
    static final int M_APP9 = 233;
    static final int M_APP10 = 234;
    static final int M_APP11 = 235;
    static final int M_APP12 = 236;
    static final int M_APP13 = 237;
    static final int M_APP14 = 238;
    static final int M_APP15 = 239;
    static final int M_JPG0 = 240;
    static final int M_JPG13 = 253;
    static final int M_COM = 254;
    static final int M_TEM = 1;
    static final int M_ERROR = 256;
    static final int CSTATE_START = 100;
    static final int CSTATE_SCANNING = 101;
    static final int CSTATE_RAW_OK = 102;
    static final int CSTATE_WRCOEFS = 103;
    static final int DSTATE_START = 200;
    static final int DSTATE_INHEADER = 201;
    static final int DSTATE_READY = 202;
    static final int DSTATE_PRELOAD = 203;
    static final int DSTATE_PRESCAN = 204;
    static final int DSTATE_SCANNING = 205;
    static final int DSTATE_RAW_OK = 206;
    static final int DSTATE_BUFIMAGE = 207;
    static final int DSTATE_BUFPOST = 208;
    static final int DSTATE_RDCOEFS = 209;
    static final int DSTATE_STOPPING = 210;
    static final int JPEG_REACHED_SOS = 1;
    static final int JPEG_REACHED_EOI = 2;
    static final int JPEG_ROW_COMPLETED = 3;
    static final int JPEG_SCAN_COMPLETED = 4;
    static final int JPEG_SUSPENDED = 0;
    static final int JPEG_HEADER_OK = 1;
    static final int JPEG_HEADER_TABLES_ONLY = 2;
    static final int DECOMPRESS_DATA = 0;
    static final int DECOMPRESS_SMOOTH_DATA = 1;
    static final int DECOMPRESS_ONEPASS = 2;
    static final int CONSUME_DATA = 0;
    static final int DUMMY_CONSUME_DATA = 1;
    static final int PROCESS_DATA_SIMPLE_MAIN = 0;
    static final int PROCESS_DATA_CONTEXT_MAIN = 1;
    static final int PROCESS_DATA_CRANK_POST = 2;
    static final int POST_PROCESS_1PASS = 0;
    static final int POST_PROCESS_DATA_UPSAMPLE = 1;
    static final int NULL_CONVERT = 0;
    static final int GRAYSCALE_CONVERT = 1;
    static final int YCC_RGB_CONVERT = 2;
    static final int GRAY_RGB_CONVERT = 3;
    static final int YCCK_CMYK_CONVERT = 4;
    static final int NOOP_UPSAMPLE = 0;
    static final int FULLSIZE_UPSAMPLE = 1;
    static final int H2V1_FANCY_UPSAMPLE = 2;
    static final int H2V1_UPSAMPLE = 3;
    static final int H2V2_FANCY_UPSAMPLE = 4;
    static final int H2V2_UPSAMPLE = 5;
    static final int INT_UPSAMPLE = 6;
    static final int INPUT_CONSUME_INPUT = 0;
    static final int COEF_CONSUME_INPUT = 1;
    static int[] extend_test;
    static int[] extend_offset;
    static int[] jpeg_natural_order;
    static final int CONST_BITS = 13;
    static final int PASS1_BITS = 2;
    static final int RANGE_MASK = 1023;
    
    static void error() {
        SWT.error(40);
    }
    
    static void error(final int code) {
        SWT.error(code);
    }
    
    static void error(final String msg) {
        SWT.error(40, null, msg);
    }
    
    static void jinit_marker_reader(final jpeg_decompress_struct cinfo) {
        final jpeg_marker_reader marker2 = new jpeg_marker_reader();
        cinfo.marker = marker2;
        final jpeg_marker_reader marker3 = marker2;
        marker3.length_limit_COM = 0;
        reset_marker_reader(cinfo);
    }
    
    static void jinit_d_coef_controller(final jpeg_decompress_struct cinfo, final boolean need_full_buffer) {
        final jpeg_d_coef_controller coef = new jpeg_d_coef_controller();
        cinfo.coef = coef;
        coef.coef_bits_latch = null;
        if (need_full_buffer) {
            for (int ci = 0; ci < cinfo.num_components; ++ci) {
                final jpeg_component_info compptr = cinfo.comp_info[ci];
                coef.whole_image[ci] = new short[(int)jround_up(compptr.height_in_blocks, compptr.v_samp_factor)][(int)jround_up(compptr.width_in_blocks, compptr.h_samp_factor)][64];
            }
            coef.decompress_data = 0;
            coef.coef_arrays = coef.whole_image[0];
        }
        else {
            coef.MCU_buffer = new short[10][64];
            coef.decompress_data = 2;
            coef.coef_arrays = null;
        }
    }
    
    static void start_output_pass(final jpeg_decompress_struct cinfo) {
        final jpeg_d_coef_controller coef = cinfo.coef;
        if (coef.coef_arrays != null) {
            if (cinfo.do_block_smoothing && smoothing_ok(cinfo)) {
                coef.decompress_data = 1;
            }
            else {
                coef.decompress_data = 0;
            }
        }
        cinfo.output_iMCU_row = 0;
    }
    
    static void jpeg_create_decompress(final jpeg_decompress_struct cinfo) {
        cinfo.is_decompressor = true;
        cinfo.marker_list = null;
        jinit_marker_reader(cinfo);
        jinit_input_controller(cinfo);
        cinfo.global_state = 200;
    }
    
    static void jpeg_calc_output_dimensions(final jpeg_decompress_struct cinfo) {
        if (cinfo.global_state != 202) {
            error();
        }
        cinfo.output_width = cinfo.image_width;
        cinfo.output_height = cinfo.image_height;
        switch (cinfo.out_color_space) {
            case 1: {
                cinfo.out_color_components = 1;
                break;
            }
            case 2:
            case 3: {
                cinfo.out_color_components = 3;
                break;
            }
            case 4:
            case 5: {
                cinfo.out_color_components = 4;
                break;
            }
            default: {
                cinfo.out_color_components = cinfo.num_components;
                break;
            }
        }
        cinfo.output_components = (cinfo.quantize_colors ? 1 : cinfo.out_color_components);
        if (use_merged_upsample(cinfo)) {
            cinfo.rec_outbuf_height = cinfo.max_v_samp_factor;
        }
        else {
            cinfo.rec_outbuf_height = 1;
        }
    }
    
    static boolean use_merged_upsample(final jpeg_decompress_struct cinfo) {
        return !cinfo.do_fancy_upsampling && !cinfo.CCIR601_sampling && cinfo.jpeg_color_space == 3 && cinfo.num_components == 3 && cinfo.out_color_space == 2 && cinfo.out_color_components == 3 && cinfo.comp_info[0].h_samp_factor == 2 && cinfo.comp_info[1].h_samp_factor == 1 && cinfo.comp_info[2].h_samp_factor == 1 && cinfo.comp_info[0].v_samp_factor <= 2 && cinfo.comp_info[1].v_samp_factor == 1 && cinfo.comp_info[2].v_samp_factor == 1 && cinfo.comp_info[0].DCT_scaled_size == cinfo.min_DCT_scaled_size && cinfo.comp_info[1].DCT_scaled_size == cinfo.min_DCT_scaled_size && cinfo.comp_info[2].DCT_scaled_size == cinfo.min_DCT_scaled_size;
    }
    
    static void prepare_range_limit_table(final jpeg_decompress_struct cinfo) {
        final byte[] table = new byte[1408];
        int offset = 256;
        cinfo.sample_range_limit_offset = offset;
        cinfo.sample_range_limit = table;
        for (int i = 0; i <= 255; ++i) {
            table[i + offset] = (byte)i;
        }
        offset += 128;
        for (int i = 128; i < 512; ++i) {
            table[i + offset] = -1;
        }
        System.arraycopy(cinfo.sample_range_limit, cinfo.sample_range_limit_offset, table, offset + 896, 128);
    }
    
    static void build_ycc_rgb_table(final jpeg_decompress_struct cinfo) {
        final jpeg_color_deconverter cconvert = cinfo.cconvert;
        cconvert.Cr_r_tab = new int[256];
        cconvert.Cb_b_tab = new int[256];
        cconvert.Cr_g_tab = new int[256];
        cconvert.Cb_g_tab = new int[256];
        for (int i = 0, x = -128; i <= 255; ++i, ++x) {
            cconvert.Cr_r_tab[i] = 91881 * x + 32768 >> 16;
            cconvert.Cb_b_tab[i] = 116130 * x + 32768 >> 16;
            cconvert.Cr_g_tab[i] = -46802 * x;
            cconvert.Cb_g_tab[i] = -22554 * x + 32768;
        }
    }
    
    static void jinit_color_deconverter(final jpeg_decompress_struct cinfo) {
        final jpeg_color_deconverter cconvert2 = new jpeg_color_deconverter();
        cinfo.cconvert = cconvert2;
        final jpeg_color_deconverter cconvert3 = cconvert2;
        switch (cinfo.jpeg_color_space) {
            case 1: {
                if (cinfo.num_components != 1) {
                    error();
                    break;
                }
                break;
            }
            case 2:
            case 3: {
                if (cinfo.num_components != 3) {
                    error();
                    break;
                }
                break;
            }
            case 4:
            case 5: {
                if (cinfo.num_components != 4) {
                    error();
                    break;
                }
                break;
            }
            default: {
                if (cinfo.num_components < 1) {
                    error();
                    break;
                }
                break;
            }
        }
        switch (cinfo.out_color_space) {
            case 1: {
                cinfo.out_color_components = 1;
                if (cinfo.jpeg_color_space == 1 || cinfo.jpeg_color_space == 3) {
                    cconvert3.color_convert = 1;
                    for (int ci = 1; ci < cinfo.num_components; ++ci) {
                        cinfo.comp_info[ci].component_needed = false;
                    }
                    break;
                }
                error();
                break;
            }
            case 2: {
                cinfo.out_color_components = 3;
                if (cinfo.jpeg_color_space == 3) {
                    cconvert3.color_convert = 2;
                    build_ycc_rgb_table(cinfo);
                    break;
                }
                if (cinfo.jpeg_color_space == 1) {
                    cconvert3.color_convert = 3;
                    break;
                }
                if (cinfo.jpeg_color_space == 2) {
                    cconvert3.color_convert = 0;
                    break;
                }
                error();
                break;
            }
            case 4: {
                cinfo.out_color_components = 4;
                if (cinfo.jpeg_color_space == 5) {
                    cconvert3.color_convert = 4;
                    build_ycc_rgb_table(cinfo);
                    break;
                }
                if (cinfo.jpeg_color_space == 4) {
                    cconvert3.color_convert = 0;
                    break;
                }
                error();
                break;
            }
            default: {
                if (cinfo.out_color_space == cinfo.jpeg_color_space) {
                    cinfo.out_color_components = cinfo.num_components;
                    cconvert3.color_convert = 0;
                    break;
                }
                error();
                break;
            }
        }
        if (cinfo.quantize_colors) {
            cinfo.output_components = 1;
        }
        else {
            cinfo.output_components = cinfo.out_color_components;
        }
    }
    
    static void jinit_d_post_controller(final jpeg_decompress_struct cinfo, final boolean need_full_buffer) {
        final jpeg_d_post_controller post2 = new jpeg_d_post_controller();
        cinfo.post = post2;
        final jpeg_d_post_controller post3 = post2;
        post3.whole_image = null;
        post3.buffer = null;
        if (cinfo.quantize_colors) {
            error(20);
        }
    }
    
    static void make_funny_pointers(final jpeg_decompress_struct cinfo) {
        final jpeg_d_main_controller main = cinfo.main;
        final int M = cinfo.min_DCT_scaled_size;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final int rgroup = compptr.v_samp_factor * compptr.DCT_scaled_size / cinfo.min_DCT_scaled_size;
            final byte[][] xbuf0 = main.xbuffer[0][ci];
            final int xbuf0_offset = main.xbuffer_offset[0][ci];
            final byte[][] xbuf2 = main.xbuffer[1][ci];
            final int xbuf1_offset = main.xbuffer_offset[1][ci];
            final byte[][] buf = main.buffer[ci];
            for (int i = 0; i < rgroup * (M + 2); ++i) {
                xbuf0[i + xbuf0_offset] = (xbuf2[i + xbuf1_offset] = buf[i]);
            }
            for (int i = 0; i < rgroup * 2; ++i) {
                xbuf2[rgroup * (M - 2) + i + xbuf1_offset] = buf[rgroup * M + i];
                xbuf2[rgroup * M + i + xbuf1_offset] = buf[rgroup * (M - 2) + i];
            }
            for (int i = 0; i < rgroup; ++i) {
                xbuf0[i - rgroup + xbuf0_offset] = xbuf0[0 + xbuf0_offset];
            }
        }
    }
    
    static void alloc_funny_pointers(final jpeg_decompress_struct cinfo) {
        final jpeg_d_main_controller main = cinfo.main;
        final int M = cinfo.min_DCT_scaled_size;
        main.xbuffer[0] = new byte[cinfo.num_components][][];
        main.xbuffer[1] = new byte[cinfo.num_components][][];
        main.xbuffer_offset[0] = new int[cinfo.num_components];
        main.xbuffer_offset[1] = new int[cinfo.num_components];
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final int rgroup = compptr.v_samp_factor * compptr.DCT_scaled_size / cinfo.min_DCT_scaled_size;
            final byte[][] xbuf = new byte[2 * (rgroup * (M + 4))][];
            int offset = rgroup;
            main.xbuffer_offset[0][ci] = offset;
            main.xbuffer[0][ci] = xbuf;
            offset += rgroup * (M + 4);
            main.xbuffer_offset[1][ci] = offset;
            main.xbuffer[1][ci] = xbuf;
        }
    }
    
    static void jinit_d_main_controller(final jpeg_decompress_struct cinfo, final boolean need_full_buffer) {
        final jpeg_d_main_controller main2 = new jpeg_d_main_controller();
        cinfo.main = main2;
        final jpeg_d_main_controller main3 = main2;
        if (need_full_buffer) {
            error();
        }
        int ngroups;
        if (cinfo.upsample.need_context_rows) {
            if (cinfo.min_DCT_scaled_size < 2) {
                error();
            }
            alloc_funny_pointers(cinfo);
            ngroups = cinfo.min_DCT_scaled_size + 2;
        }
        else {
            ngroups = cinfo.min_DCT_scaled_size;
        }
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final int rgroup = compptr.v_samp_factor * compptr.DCT_scaled_size / cinfo.min_DCT_scaled_size;
            main3.buffer[ci] = new byte[rgroup * ngroups][compptr.width_in_blocks * compptr.DCT_scaled_size];
        }
    }
    
    static long jround_up(long a, final long b) {
        a += b - 1L;
        return a - a % b;
    }
    
    static void jinit_upsampler(final jpeg_decompress_struct cinfo) {
        final jpeg_upsampler upsample = new jpeg_upsampler();
        cinfo.upsample = upsample;
        upsample.need_context_rows = false;
        if (cinfo.CCIR601_sampling) {
            error();
        }
        final boolean do_fancy = cinfo.do_fancy_upsampling && cinfo.min_DCT_scaled_size > 1;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final int h_in_group = compptr.h_samp_factor * compptr.DCT_scaled_size / cinfo.min_DCT_scaled_size;
            final int v_in_group = compptr.v_samp_factor * compptr.DCT_scaled_size / cinfo.min_DCT_scaled_size;
            final int h_out_group = cinfo.max_h_samp_factor;
            final int v_out_group = cinfo.max_v_samp_factor;
            upsample.rowgroup_height[ci] = v_in_group;
            boolean need_buffer = true;
            if (!compptr.component_needed) {
                upsample.methods[ci] = 0;
                need_buffer = false;
            }
            else if (h_in_group == h_out_group && v_in_group == v_out_group) {
                upsample.methods[ci] = 1;
                need_buffer = false;
            }
            else if (h_in_group * 2 == h_out_group && v_in_group == v_out_group) {
                if (do_fancy && compptr.downsampled_width > 2) {
                    upsample.methods[ci] = 2;
                }
                else {
                    upsample.methods[ci] = 3;
                }
            }
            else if (h_in_group * 2 == h_out_group && v_in_group * 2 == v_out_group) {
                if (do_fancy && compptr.downsampled_width > 2) {
                    upsample.methods[ci] = 4;
                    upsample.need_context_rows = true;
                }
                else {
                    upsample.methods[ci] = 5;
                }
            }
            else if (h_out_group % h_in_group == 0 && v_out_group % v_in_group == 0) {
                upsample.methods[ci] = 6;
                upsample.h_expand[ci] = (byte)(h_out_group / h_in_group);
                upsample.v_expand[ci] = (byte)(v_out_group / v_in_group);
            }
            else {
                error();
            }
            if (need_buffer) {
                upsample.color_buf[ci] = new byte[cinfo.max_v_samp_factor][(int)jround_up(cinfo.output_width, cinfo.max_h_samp_factor)];
            }
        }
    }
    
    static void jinit_phuff_decoder(final jpeg_decompress_struct cinfo) {
        cinfo.entropy = new phuff_entropy_decoder();
        cinfo.coef_bits = new int[cinfo.num_components][64];
        final int[][] coef_bit_ptr = cinfo.coef_bits;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            for (int i = 0; i < 64; ++i) {
                coef_bit_ptr[ci][i] = -1;
            }
        }
    }
    
    static void jinit_huff_decoder(final jpeg_decompress_struct cinfo) {
        cinfo.entropy = new huff_entropy_decoder();
    }
    
    static void jinit_inverse_dct(final jpeg_decompress_struct cinfo) {
        final jpeg_inverse_dct idct2 = new jpeg_inverse_dct();
        cinfo.idct = idct2;
        final jpeg_inverse_dct idct3 = idct2;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            compptr.dct_table = new int[64];
            idct3.cur_method[ci] = -1;
        }
    }
    
    static void jpeg_idct_islow(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final short[] coef_block, final byte[][] output_buf, final int output_buf_offset, final int output_col) {
        final byte[] range_limit = cinfo.sample_range_limit;
        final int range_limit_offset = cinfo.sample_range_limit_offset + 128;
        final int[] workspace = cinfo.workspace;
        final short[] inptr = coef_block;
        final int[] quantptr = compptr.dct_table;
        int[] wsptr = workspace;
        int inptr_offset = 0;
        int quantptr_offset = 0;
        int wsptr_offset = 0;
        for (int ctr = 8; ctr > 0; --ctr) {
            if (inptr[8 + inptr_offset] == 0 && inptr[16 + inptr_offset] == 0 && inptr[24 + inptr_offset] == 0 && inptr[32 + inptr_offset] == 0 && inptr[40 + inptr_offset] == 0 && inptr[48 + inptr_offset] == 0 && inptr[56 + inptr_offset] == 0) {
                final int dcval = inptr[0 + inptr_offset] * quantptr[0 + quantptr_offset] << 2;
                wsptr[8 + wsptr_offset] = (wsptr[0 + wsptr_offset] = dcval);
                wsptr[24 + wsptr_offset] = (wsptr[16 + wsptr_offset] = dcval);
                wsptr[40 + wsptr_offset] = (wsptr[32 + wsptr_offset] = dcval);
                wsptr[56 + wsptr_offset] = (wsptr[48 + wsptr_offset] = dcval);
                ++inptr_offset;
                ++quantptr_offset;
                ++wsptr_offset;
            }
            else {
                int z2 = inptr[16 + inptr_offset] * quantptr[16 + quantptr_offset];
                int z3 = inptr[48 + inptr_offset] * quantptr[48 + quantptr_offset];
                int z4 = (z2 + z3) * 4433;
                int tmp2 = z4 + z3 * -15137;
                int tmp3 = z4 + z2 * 6270;
                z2 = inptr[0 + inptr_offset] * quantptr[0 + quantptr_offset];
                z3 = inptr[32 + inptr_offset] * quantptr[32 + quantptr_offset];
                int tmp4 = z2 + z3 << 13;
                int tmp5 = z2 - z3 << 13;
                final int tmp6 = tmp4 + tmp3;
                final int tmp7 = tmp4 - tmp3;
                final int tmp8 = tmp5 + tmp2;
                final int tmp9 = tmp5 - tmp2;
                tmp4 = inptr[56 + inptr_offset] * quantptr[56 + quantptr_offset];
                tmp5 = inptr[40 + inptr_offset] * quantptr[40 + quantptr_offset];
                tmp2 = inptr[24 + inptr_offset] * quantptr[24 + quantptr_offset];
                tmp3 = inptr[8 + inptr_offset] * quantptr[8 + quantptr_offset];
                z4 = tmp4 + tmp3;
                z2 = tmp5 + tmp2;
                z3 = tmp4 + tmp2;
                int z5 = tmp5 + tmp3;
                final int z6 = (z3 + z5) * 9633;
                tmp4 *= 2446;
                tmp5 *= 16819;
                tmp2 *= 25172;
                tmp3 *= 12299;
                z4 *= -7373;
                z2 *= -20995;
                z3 *= -16069;
                z5 *= -3196;
                z3 += z6;
                z5 += z6;
                tmp4 += z4 + z3;
                tmp5 += z2 + z5;
                tmp2 += z2 + z3;
                tmp3 += z4 + z5;
                wsptr[0 + wsptr_offset] = tmp6 + tmp3 + 1024 >> 11;
                wsptr[56 + wsptr_offset] = tmp6 - tmp3 + 1024 >> 11;
                wsptr[8 + wsptr_offset] = tmp8 + tmp2 + 1024 >> 11;
                wsptr[48 + wsptr_offset] = tmp8 - tmp2 + 1024 >> 11;
                wsptr[16 + wsptr_offset] = tmp9 + tmp5 + 1024 >> 11;
                wsptr[40 + wsptr_offset] = tmp9 - tmp5 + 1024 >> 11;
                wsptr[24 + wsptr_offset] = tmp7 + tmp4 + 1024 >> 11;
                wsptr[32 + wsptr_offset] = tmp7 - tmp4 + 1024 >> 11;
                ++inptr_offset;
                ++quantptr_offset;
                ++wsptr_offset;
            }
        }
        int outptr_offset = 0;
        wsptr = workspace;
        wsptr_offset = 0;
        for (int ctr2 = 0; ctr2 < 8; ++ctr2) {
            final byte[] outptr = output_buf[ctr2 + output_buf_offset];
            outptr_offset = output_col;
            if (wsptr[1 + wsptr_offset] == 0 && wsptr[2 + wsptr_offset] == 0 && wsptr[3 + wsptr_offset] == 0 && wsptr[4 + wsptr_offset] == 0 && wsptr[5 + wsptr_offset] == 0 && wsptr[6 + wsptr_offset] == 0 && wsptr[7 + wsptr_offset] == 0) {
                final byte dcval2 = range_limit[range_limit_offset + (wsptr[0 + wsptr_offset] + 16 >> 5 & 0x3FF)];
                outptr[1 + outptr_offset] = (outptr[0 + outptr_offset] = dcval2);
                outptr[3 + outptr_offset] = (outptr[2 + outptr_offset] = dcval2);
                outptr[5 + outptr_offset] = (outptr[4 + outptr_offset] = dcval2);
                outptr[7 + outptr_offset] = (outptr[6 + outptr_offset] = dcval2);
                wsptr_offset += 8;
            }
            else {
                int z7 = wsptr[2 + wsptr_offset];
                int z8 = wsptr[6 + wsptr_offset];
                int z9 = (z7 + z8) * 4433;
                int tmp10 = z9 + z8 * -15137;
                int tmp11 = z9 + z7 * 6270;
                int tmp12 = wsptr[0 + wsptr_offset] + wsptr[4 + wsptr_offset] << 13;
                int tmp13 = wsptr[0 + wsptr_offset] - wsptr[4 + wsptr_offset] << 13;
                final int tmp14 = tmp12 + tmp11;
                final int tmp15 = tmp12 - tmp11;
                final int tmp16 = tmp13 + tmp10;
                final int tmp17 = tmp13 - tmp10;
                tmp12 = wsptr[7 + wsptr_offset];
                tmp13 = wsptr[5 + wsptr_offset];
                tmp10 = wsptr[3 + wsptr_offset];
                tmp11 = wsptr[1 + wsptr_offset];
                z9 = tmp12 + tmp11;
                z7 = tmp13 + tmp10;
                z8 = tmp12 + tmp10;
                int z10 = tmp13 + tmp11;
                final int z11 = (z8 + z10) * 9633;
                tmp12 *= 2446;
                tmp13 *= 16819;
                tmp10 *= 25172;
                tmp11 *= 12299;
                z9 *= -7373;
                z7 *= -20995;
                z8 *= -16069;
                z10 *= -3196;
                z8 += z11;
                z10 += z11;
                tmp12 += z9 + z8;
                tmp13 += z7 + z10;
                tmp10 += z7 + z8;
                tmp11 += z9 + z10;
                outptr[0 + outptr_offset] = range_limit[range_limit_offset + (tmp14 + tmp11 + 131072 >> 18 & 0x3FF)];
                outptr[7 + outptr_offset] = range_limit[range_limit_offset + (tmp14 - tmp11 + 131072 >> 18 & 0x3FF)];
                outptr[1 + outptr_offset] = range_limit[range_limit_offset + (tmp16 + tmp10 + 131072 >> 18 & 0x3FF)];
                outptr[6 + outptr_offset] = range_limit[range_limit_offset + (tmp16 - tmp10 + 131072 >> 18 & 0x3FF)];
                outptr[2 + outptr_offset] = range_limit[range_limit_offset + (tmp17 + tmp13 + 131072 >> 18 & 0x3FF)];
                outptr[5 + outptr_offset] = range_limit[range_limit_offset + (tmp17 - tmp13 + 131072 >> 18 & 0x3FF)];
                outptr[3 + outptr_offset] = range_limit[range_limit_offset + (tmp15 + tmp12 + 131072 >> 18 & 0x3FF)];
                outptr[4 + outptr_offset] = range_limit[range_limit_offset + (tmp15 - tmp12 + 131072 >> 18 & 0x3FF)];
                wsptr_offset += 8;
            }
        }
    }
    
    static void upsample(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, final int[] in_row_group_ctr, final int in_row_groups_avail, final byte[][] output_buf, final int[] out_row_ctr, final int out_rows_avail) {
        sep_upsample(cinfo, input_buf, input_buf_offset, in_row_group_ctr, in_row_groups_avail, output_buf, out_row_ctr, out_rows_avail);
    }
    
    static boolean smoothing_ok(final jpeg_decompress_struct cinfo) {
        final jpeg_d_coef_controller coef = cinfo.coef;
        boolean smoothing_useful = false;
        if (!cinfo.progressive_mode || cinfo.coef_bits == null) {
            return false;
        }
        if (coef.coef_bits_latch == null) {
            coef.coef_bits_latch = new int[cinfo.num_components * 6];
        }
        final int[] coef_bits_latch = coef.coef_bits_latch;
        int coef_bits_latch_offset = 0;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final JQUANT_TBL qtable;
            if ((qtable = compptr.quant_table) == null) {
                return false;
            }
            if (qtable.quantval[0] == 0 || qtable.quantval[1] == 0 || qtable.quantval[8] == 0 || qtable.quantval[16] == 0 || qtable.quantval[9] == 0 || qtable.quantval[2] == 0) {
                return false;
            }
            final int[] coef_bits = cinfo.coef_bits[ci];
            if (coef_bits[0] < 0) {
                return false;
            }
            for (int coefi = 1; coefi <= 5; ++coefi) {
                coef_bits_latch[coefi + coef_bits_latch_offset] = coef_bits[coefi];
                if (coef_bits[coefi] != 0) {
                    smoothing_useful = true;
                }
            }
            coef_bits_latch_offset += 6;
        }
        return smoothing_useful;
    }
    
    static void master_selection(final jpeg_decompress_struct cinfo) {
        final jpeg_decomp_master master = cinfo.master;
        jpeg_calc_output_dimensions(cinfo);
        prepare_range_limit_table(cinfo);
        final long samplesperrow = cinfo.output_width * (long)cinfo.out_color_components;
        final int jd_samplesperrow = (int)samplesperrow;
        if (jd_samplesperrow != samplesperrow) {
            error();
        }
        master.pass_number = 0;
        master.using_merged_upsample = use_merged_upsample(cinfo);
        master.quantizer_1pass = null;
        master.quantizer_2pass = null;
        if (!cinfo.quantize_colors || !cinfo.buffered_image) {
            cinfo.enable_1pass_quant = false;
            cinfo.enable_external_quant = false;
            cinfo.enable_2pass_quant = false;
        }
        if (cinfo.quantize_colors) {
            error(20);
        }
        if (!cinfo.raw_data_out) {
            if (master.using_merged_upsample) {
                error();
            }
            else {
                jinit_color_deconverter(cinfo);
                jinit_upsampler(cinfo);
            }
            jinit_d_post_controller(cinfo, cinfo.enable_2pass_quant);
        }
        jinit_inverse_dct(cinfo);
        if (cinfo.arith_code) {
            error();
        }
        else if (cinfo.progressive_mode) {
            jinit_phuff_decoder(cinfo);
        }
        else {
            jinit_huff_decoder(cinfo);
        }
        final boolean use_c_buffer = cinfo.inputctl.has_multiple_scans || cinfo.buffered_image;
        jinit_d_coef_controller(cinfo, use_c_buffer);
        if (!cinfo.raw_data_out) {
            jinit_d_main_controller(cinfo, false);
        }
        start_input_pass(cinfo);
    }
    
    static void jinit_master_decompress(final jpeg_decompress_struct cinfo) {
        final jpeg_decomp_master master = new jpeg_decomp_master();
        cinfo.master = master;
        master.is_dummy_pass = false;
        master_selection(cinfo);
    }
    
    static void jcopy_sample_rows(final byte[][] input_array, final int source_row, final byte[][] output_array, final int dest_row, final int num_rows, final int num_cols) {
        final int count = num_cols;
        int input_array_offset = source_row;
        int output_array_offset = dest_row;
        for (int row = num_rows; row > 0; --row) {
            final byte[] inptr = input_array[input_array_offset++];
            final byte[] outptr = output_array[output_array_offset++];
            System.arraycopy(inptr, 0, outptr, 0, count);
        }
    }
    
    static boolean jpeg_start_decompress(final jpeg_decompress_struct cinfo) {
        if (cinfo.global_state == 202) {
            jinit_master_decompress(cinfo);
            if (cinfo.buffered_image) {
                cinfo.global_state = 207;
                return true;
            }
            cinfo.global_state = 203;
        }
        if (cinfo.global_state == 203) {
            if (cinfo.inputctl.has_multiple_scans) {
                int retcode;
                do {
                    retcode = consume_input(cinfo);
                    if (retcode == 0) {
                        return false;
                    }
                } while (retcode != 2);
            }
            cinfo.output_scan_number = cinfo.input_scan_number;
        }
        else if (cinfo.global_state != 204) {
            error();
        }
        return output_pass_setup(cinfo);
    }
    
    static void prepare_for_output_pass(final jpeg_decompress_struct cinfo) {
        final jpeg_decomp_master master = cinfo.master;
        if (master.is_dummy_pass) {
            error(20);
        }
        else {
            if (cinfo.quantize_colors && cinfo.colormap == null) {
                if (cinfo.two_pass_quantize && cinfo.enable_2pass_quant) {
                    cinfo.cquantize = master.quantizer_2pass;
                    master.is_dummy_pass = true;
                }
                else if (cinfo.enable_1pass_quant) {
                    cinfo.cquantize = master.quantizer_1pass;
                }
                else {
                    error();
                }
            }
            cinfo.idct.start_pass(cinfo);
            start_output_pass(cinfo);
            if (!cinfo.raw_data_out) {
                if (!master.using_merged_upsample) {
                    cinfo.cconvert.start_pass(cinfo);
                }
                cinfo.upsample.start_pass(cinfo);
                if (cinfo.quantize_colors) {
                    cinfo.cquantize.start_pass(cinfo, master.is_dummy_pass);
                }
                cinfo.post.start_pass(cinfo, master.is_dummy_pass ? 3 : 0);
                cinfo.main.start_pass(cinfo, 0);
            }
        }
    }
    
    static boolean jpeg_resync_to_restart(final jpeg_decompress_struct cinfo, final int desired) {
        int marker = cinfo.unread_marker;
        int action = 1;
        while (true) {
            if (marker < 192) {
                action = 2;
            }
            else if (marker < 208 || marker > 215) {
                action = 3;
            }
            else if (marker == 208 + (desired + 1 & 0x7) || marker == 208 + (desired + 2 & 0x7)) {
                action = 3;
            }
            else if (marker == 208 + (desired - 1 & 0x7) || marker == 208 + (desired - 2 & 0x7)) {
                action = 2;
            }
            else {
                action = 1;
            }
            switch (action) {
                case 1: {
                    cinfo.unread_marker = 0;
                    return true;
                }
                case 2: {
                    if (!next_marker(cinfo)) {
                        return false;
                    }
                    marker = cinfo.unread_marker;
                    continue;
                }
                case 3: {
                    return true;
                }
                default: {
                    continue;
                }
            }
        }
    }
    
    static boolean read_restart_marker(final jpeg_decompress_struct cinfo) {
        if (cinfo.unread_marker == 0 && !next_marker(cinfo)) {
            return false;
        }
        if (cinfo.unread_marker == 208 + cinfo.marker.next_restart_num) {
            cinfo.unread_marker = 0;
        }
        else if (!jpeg_resync_to_restart(cinfo, cinfo.marker.next_restart_num)) {
            return false;
        }
        cinfo.marker.next_restart_num = (cinfo.marker.next_restart_num + 1 & 0x7);
        return true;
    }
    
    static boolean jpeg_fill_bit_buffer(final bitread_working_state state, int get_buffer, int bits_left, final int nbits) {
        byte[] buffer = state.buffer;
        int bytes_in_buffer = state.bytes_in_buffer;
        int bytes_offset = state.bytes_offset;
        final jpeg_decompress_struct cinfo = state.cinfo;
        if (cinfo.unread_marker == 0) {
            while (bits_left < 25) {
                if (bytes_offset == bytes_in_buffer) {
                    if (!fill_input_buffer(cinfo)) {
                        return false;
                    }
                    buffer = cinfo.buffer;
                    bytes_in_buffer = cinfo.bytes_in_buffer;
                    bytes_offset = cinfo.bytes_offset;
                }
                int c = buffer[bytes_offset++] & 0xFF;
                if (c == 255) {
                    do {
                        if (bytes_offset == bytes_in_buffer) {
                            if (!fill_input_buffer(cinfo)) {
                                return false;
                            }
                            buffer = cinfo.buffer;
                            bytes_in_buffer = cinfo.bytes_in_buffer;
                            bytes_offset = cinfo.bytes_offset;
                        }
                        c = (buffer[bytes_offset++] & 0xFF);
                    } while (c == 255);
                    if (c != 0) {
                        cinfo.unread_marker = c;
                        if (nbits > bits_left) {
                            if (!cinfo.entropy.insufficient_data) {
                                cinfo.entropy.insufficient_data = true;
                            }
                            get_buffer <<= 25 - bits_left;
                            bits_left = 25;
                        }
                        state.buffer = buffer;
                        state.bytes_in_buffer = bytes_in_buffer;
                        state.bytes_offset = bytes_offset;
                        state.get_buffer = get_buffer;
                        state.bits_left = bits_left;
                        return true;
                    }
                    c = 255;
                }
                get_buffer = (get_buffer << 8 | c);
                bits_left += 8;
            }
        }
        else if (nbits > bits_left) {
            if (!cinfo.entropy.insufficient_data) {
                cinfo.entropy.insufficient_data = true;
            }
            get_buffer <<= 25 - bits_left;
            bits_left = 25;
        }
        state.buffer = buffer;
        state.bytes_in_buffer = bytes_in_buffer;
        state.bytes_offset = bytes_offset;
        state.get_buffer = get_buffer;
        state.bits_left = bits_left;
        return true;
    }
    
    static int jpeg_huff_decode(final bitread_working_state state, int get_buffer, int bits_left, final d_derived_tbl htbl, final int min_bits) {
        int l = min_bits;
        if (bits_left < l) {
            if (!jpeg_fill_bit_buffer(state, get_buffer, bits_left, l)) {
                return -1;
            }
            get_buffer = state.get_buffer;
            bits_left = state.bits_left;
        }
        int code;
        for (code = (get_buffer >> (bits_left -= l) & (1 << l) - 1); code > htbl.maxcode[l]; code |= (get_buffer >> --bits_left & 0x1), ++l) {
            code <<= 1;
            if (bits_left < 1) {
                if (!jpeg_fill_bit_buffer(state, get_buffer, bits_left, 1)) {
                    return -1;
                }
                get_buffer = state.get_buffer;
                bits_left = state.bits_left;
            }
        }
        state.get_buffer = get_buffer;
        state.bits_left = bits_left;
        if (l > 16) {
            return 0;
        }
        return htbl.pub.huffval[code + htbl.valoffset[l]] & 0xFF;
    }
    
    static int decompress_onepass(final jpeg_decompress_struct cinfo, final byte[][][] output_buf, final int[] output_buf_offset) {
        final jpeg_d_coef_controller coef = cinfo.coef;
        final int last_MCU_col = cinfo.MCUs_per_row - 1;
        final int last_iMCU_row = cinfo.total_iMCU_rows - 1;
        for (int yoffset = coef.MCU_vert_offset; yoffset < coef.MCU_rows_per_iMCU_row; ++yoffset) {
            for (int MCU_col_num = coef.MCU_ctr; MCU_col_num <= last_MCU_col; ++MCU_col_num) {
                for (int i = 0; i < cinfo.blocks_in_MCU; ++i) {
                    final short[] blk = coef.MCU_buffer[i];
                    for (int j = 0; j < blk.length; ++j) {
                        blk[j] = 0;
                    }
                }
                if (!cinfo.entropy.decode_mcu(cinfo, coef.MCU_buffer)) {
                    coef.MCU_vert_offset = yoffset;
                    coef.MCU_ctr = MCU_col_num;
                    return 0;
                }
                int blkn = 0;
                for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                    final jpeg_component_info compptr = cinfo.cur_comp_info[ci];
                    if (!compptr.component_needed) {
                        blkn += compptr.MCU_blocks;
                    }
                    else {
                        final int useful_width = (MCU_col_num < last_MCU_col) ? compptr.MCU_width : compptr.last_col_width;
                        final byte[][] output_ptr = output_buf[compptr.component_index];
                        int output_ptr_offset = output_buf_offset[compptr.component_index] + yoffset * compptr.DCT_scaled_size;
                        final int start_col = MCU_col_num * compptr.MCU_sample_width;
                        for (int yindex = 0; yindex < compptr.MCU_height; ++yindex) {
                            if (cinfo.input_iMCU_row < last_iMCU_row || yoffset + yindex < compptr.last_row_height) {
                                int output_col = start_col;
                                for (int xindex = 0; xindex < useful_width; ++xindex) {
                                    jpeg_idct_islow(cinfo, compptr, coef.MCU_buffer[blkn + xindex], output_ptr, output_ptr_offset, output_col);
                                    output_col += compptr.DCT_scaled_size;
                                }
                            }
                            blkn += compptr.MCU_width;
                            output_ptr_offset += compptr.DCT_scaled_size;
                        }
                    }
                }
            }
            coef.MCU_ctr = 0;
        }
        ++cinfo.output_iMCU_row;
        if (++cinfo.input_iMCU_row < cinfo.total_iMCU_rows) {
            coef.start_iMCU_row(cinfo);
            return 3;
        }
        finish_input_pass(cinfo);
        return 4;
    }
    
    static int decompress_smooth_data(final jpeg_decompress_struct cinfo, final byte[][][] output_buf, final int[] output_buf_offset) {
        final jpeg_d_coef_controller coef = cinfo.coef;
        final int last_iMCU_row = cinfo.total_iMCU_rows - 1;
        short[] workspace = coef.workspace;
        if (workspace == null) {
            final jpeg_d_coef_controller jpeg_d_coef_controller = coef;
            final short[] workspace2 = new short[64];
            jpeg_d_coef_controller.workspace = workspace2;
            workspace = workspace2;
        }
        while (cinfo.input_scan_number <= cinfo.output_scan_number && !cinfo.inputctl.eoi_reached) {
            if (cinfo.input_scan_number == cinfo.output_scan_number) {
                final int delta = (cinfo.Ss == 0) ? 1 : 0;
                if (cinfo.input_iMCU_row > cinfo.output_iMCU_row + delta) {
                    break;
                }
            }
            if (consume_input(cinfo) == 0) {
                return 0;
            }
        }
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            if (compptr.component_needed) {
                int block_rows;
                boolean last_row;
                if (cinfo.output_iMCU_row < last_iMCU_row) {
                    block_rows = compptr.v_samp_factor;
                    last_row = false;
                }
                else {
                    block_rows = compptr.height_in_blocks % compptr.v_samp_factor;
                    if (block_rows == 0) {
                        block_rows = compptr.v_samp_factor;
                    }
                    last_row = true;
                }
                short[][][] buffer;
                int buffer_offset;
                boolean first_row;
                if (cinfo.output_iMCU_row > 0) {
                    buffer = coef.whole_image[ci];
                    buffer_offset = (cinfo.output_iMCU_row - 1) * compptr.v_samp_factor;
                    buffer_offset += compptr.v_samp_factor;
                    first_row = false;
                }
                else {
                    buffer = coef.whole_image[ci];
                    buffer_offset = 0;
                    first_row = true;
                }
                final int[] coef_bits = coef.coef_bits_latch;
                final int coef_offset = ci * 6;
                final JQUANT_TBL quanttbl = compptr.quant_table;
                final int Q00 = quanttbl.quantval[0];
                final int Q2 = quanttbl.quantval[1];
                final int Q3 = quanttbl.quantval[8];
                final int Q4 = quanttbl.quantval[16];
                final int Q5 = quanttbl.quantval[9];
                final int Q6 = quanttbl.quantval[2];
                final byte[][] output_ptr = output_buf[ci];
                int output_ptr_offset = output_buf_offset[ci];
                for (int block_row = 0; block_row < block_rows; ++block_row) {
                    final short[][] buffer_ptr = buffer[block_row + buffer_offset];
                    int buffer_ptr_offset = 0;
                    int prev_block_row_offset = 0;
                    int next_block_row_offset = 0;
                    short[][] prev_block_row;
                    if (first_row && block_row == 0) {
                        prev_block_row = buffer_ptr;
                        prev_block_row_offset = buffer_ptr_offset;
                    }
                    else {
                        prev_block_row = buffer[block_row - 1 + buffer_offset];
                        prev_block_row_offset = 0;
                    }
                    short[][] next_block_row;
                    if (last_row && block_row == block_rows - 1) {
                        next_block_row = buffer_ptr;
                        next_block_row_offset = buffer_ptr_offset;
                    }
                    else {
                        next_block_row = buffer[block_row + 1 + buffer_offset];
                        next_block_row_offset = 0;
                    }
                    int DC5;
                    int DC4;
                    int DC3 = DC4 = (DC5 = prev_block_row[0 + prev_block_row_offset][0]);
                    int DC8;
                    int DC7;
                    int DC6 = DC7 = (DC8 = buffer_ptr[0 + buffer_ptr_offset][0]);
                    int DC11;
                    int DC10;
                    int DC9 = DC10 = (DC11 = next_block_row[0 + next_block_row_offset][0]);
                    int output_col = 0;
                    for (int last_block_column = compptr.width_in_blocks - 1, block_num = 0; block_num <= last_block_column; ++block_num) {
                        System.arraycopy(buffer_ptr[buffer_ptr_offset], 0, workspace, 0, workspace.length);
                        if (block_num < last_block_column) {
                            DC5 = prev_block_row[1 + prev_block_row_offset][0];
                            DC8 = buffer_ptr[1 + buffer_ptr_offset][0];
                            DC11 = next_block_row[1 + next_block_row_offset][0];
                        }
                        int Al;
                        if ((Al = coef_bits[1 + coef_offset]) != 0 && workspace[1] == 0) {
                            final int num = 36 * Q00 * (DC6 - DC8);
                            int pred;
                            if (num >= 0) {
                                pred = ((Q2 << 7) + num) / (Q2 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                            }
                            else {
                                pred = ((Q2 << 7) - num) / (Q2 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                                pred = -pred;
                            }
                            workspace[1] = (short)pred;
                        }
                        if ((Al = coef_bits[2 + coef_offset]) != 0 && workspace[8] == 0) {
                            final int num = 36 * Q00 * (DC4 - DC10);
                            int pred;
                            if (num >= 0) {
                                pred = ((Q3 << 7) + num) / (Q3 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                            }
                            else {
                                pred = ((Q3 << 7) - num) / (Q3 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                                pred = -pred;
                            }
                            workspace[8] = (short)pred;
                        }
                        if ((Al = coef_bits[3 + coef_offset]) != 0 && workspace[16] == 0) {
                            final int num = 9 * Q00 * (DC4 + DC10 - 2 * DC7);
                            int pred;
                            if (num >= 0) {
                                pred = ((Q4 << 7) + num) / (Q4 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                            }
                            else {
                                pred = ((Q4 << 7) - num) / (Q4 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                                pred = -pred;
                            }
                            workspace[16] = (short)pred;
                        }
                        if ((Al = coef_bits[4 + coef_offset]) != 0 && workspace[9] == 0) {
                            final int num = 5 * Q00 * (DC3 - DC5 - DC9 + DC11);
                            int pred;
                            if (num >= 0) {
                                pred = ((Q5 << 7) + num) / (Q5 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                            }
                            else {
                                pred = ((Q5 << 7) - num) / (Q5 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                                pred = -pred;
                            }
                            workspace[9] = (short)pred;
                        }
                        if ((Al = coef_bits[5 + coef_offset]) != 0 && workspace[2] == 0) {
                            final int num = 9 * Q00 * (DC6 + DC8 - 2 * DC7);
                            int pred;
                            if (num >= 0) {
                                pred = ((Q6 << 7) + num) / (Q6 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                            }
                            else {
                                pred = ((Q6 << 7) - num) / (Q6 << 8);
                                if (Al > 0 && pred >= 1 << Al) {
                                    pred = (1 << Al) - 1;
                                }
                                pred = -pred;
                            }
                            workspace[2] = (short)pred;
                        }
                        jpeg_idct_islow(cinfo, compptr, workspace, output_ptr, output_ptr_offset, output_col);
                        DC3 = DC4;
                        DC4 = DC5;
                        DC6 = DC7;
                        DC7 = DC8;
                        DC9 = DC10;
                        DC10 = DC11;
                        ++buffer_ptr_offset;
                        ++prev_block_row_offset;
                        ++next_block_row_offset;
                        output_col += compptr.DCT_scaled_size;
                    }
                    output_ptr_offset += compptr.DCT_scaled_size;
                }
            }
        }
        if (++cinfo.output_iMCU_row < cinfo.total_iMCU_rows) {
            return 3;
        }
        return 4;
    }
    
    static int decompress_data(final jpeg_decompress_struct cinfo, final byte[][][] output_buf, final int[] output_buf_offset) {
        final jpeg_d_coef_controller coef = cinfo.coef;
        final int last_iMCU_row = cinfo.total_iMCU_rows - 1;
        while (cinfo.input_scan_number < cinfo.output_scan_number || (cinfo.input_scan_number == cinfo.output_scan_number && cinfo.input_iMCU_row <= cinfo.output_iMCU_row)) {
            if (consume_input(cinfo) == 0) {
                return 0;
            }
        }
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            if (compptr.component_needed) {
                final short[][][] buffer = coef.whole_image[ci];
                final int buffer_offset = cinfo.output_iMCU_row * compptr.v_samp_factor;
                int block_rows;
                if (cinfo.output_iMCU_row < last_iMCU_row) {
                    block_rows = compptr.v_samp_factor;
                }
                else {
                    block_rows = compptr.height_in_blocks % compptr.v_samp_factor;
                    if (block_rows == 0) {
                        block_rows = compptr.v_samp_factor;
                    }
                }
                final byte[][] output_ptr = output_buf[ci];
                int output_ptr_offset = output_buf_offset[ci];
                for (int block_row = 0; block_row < block_rows; ++block_row) {
                    final short[][] buffer_ptr = buffer[block_row + buffer_offset];
                    int buffer_ptr_offset = 0;
                    int output_col = 0;
                    for (int block_num = 0; block_num < compptr.width_in_blocks; ++block_num) {
                        jpeg_idct_islow(cinfo, compptr, buffer_ptr[buffer_ptr_offset], output_ptr, output_ptr_offset, output_col);
                        ++buffer_ptr_offset;
                        output_col += compptr.DCT_scaled_size;
                    }
                    output_ptr_offset += compptr.DCT_scaled_size;
                }
            }
        }
        if (++cinfo.output_iMCU_row < cinfo.total_iMCU_rows) {
            return 3;
        }
        return 4;
    }
    
    static void post_process_data(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, final int[] in_row_group_ctr, final int in_row_groups_avail, final byte[][] output_buf, final int[] out_row_ctr, final int out_rows_avail) {
        upsample(cinfo, input_buf, input_buf_offset, in_row_group_ctr, in_row_groups_avail, output_buf, out_row_ctr, out_rows_avail);
    }
    
    static void set_bottom_pointers(final jpeg_decompress_struct cinfo) {
        final jpeg_d_main_controller main = cinfo.main;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final int iMCUheight = compptr.v_samp_factor * compptr.DCT_scaled_size;
            final int rgroup = iMCUheight / cinfo.min_DCT_scaled_size;
            int rows_left = compptr.downsampled_height % iMCUheight;
            if (rows_left == 0) {
                rows_left = iMCUheight;
            }
            if (ci == 0) {
                main.rowgroups_avail = (rows_left - 1) / rgroup + 1;
            }
            final byte[][] xbuf = main.xbuffer[main.whichptr][ci];
            final int xbuf_offset = main.xbuffer_offset[main.whichptr][ci];
            for (int i = 0; i < rgroup * 2; ++i) {
                xbuf[rows_left + i + xbuf_offset] = xbuf[rows_left - 1 + xbuf_offset];
            }
        }
    }
    
    static void set_wraparound_pointers(final jpeg_decompress_struct cinfo) {
        final jpeg_d_main_controller main = cinfo.main;
        final int M = cinfo.min_DCT_scaled_size;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            final int rgroup = compptr.v_samp_factor * compptr.DCT_scaled_size / cinfo.min_DCT_scaled_size;
            final byte[][] xbuf0 = main.xbuffer[0][ci];
            final int xbuf0_offset = main.xbuffer_offset[0][ci];
            final byte[][] xbuf2 = main.xbuffer[1][ci];
            final int xbuf1_offset = main.xbuffer_offset[1][ci];
            for (int i = 0; i < rgroup; ++i) {
                xbuf0[i - rgroup + xbuf0_offset] = xbuf0[rgroup * (M + 1) + i + xbuf0_offset];
                xbuf2[i - rgroup + xbuf1_offset] = xbuf2[rgroup * (M + 1) + i + xbuf1_offset];
                xbuf0[rgroup * (M + 2) + i + xbuf0_offset] = xbuf0[i + xbuf0_offset];
                xbuf2[rgroup * (M + 2) + i + xbuf1_offset] = xbuf2[i + xbuf1_offset];
            }
        }
    }
    
    static void process_data_crank_post(final jpeg_decompress_struct cinfo, final byte[][] output_buf, final int[] out_row_ctr, final int out_rows_avail) {
        error();
    }
    
    static void process_data_context_main(final jpeg_decompress_struct cinfo, final byte[][] output_buf, final int[] out_row_ctr, final int out_rows_avail) {
        final jpeg_d_main_controller main = cinfo.main;
        if (!main.buffer_full) {
            int result = 0;
            switch (cinfo.coef.decompress_data) {
                case 0: {
                    result = decompress_data(cinfo, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr]);
                    break;
                }
                case 1: {
                    result = decompress_smooth_data(cinfo, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr]);
                    break;
                }
                case 2: {
                    result = decompress_onepass(cinfo, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr]);
                    break;
                }
                default: {
                    result = 0;
                    break;
                }
            }
            if (result == 0) {
                return;
            }
            main.buffer_full = true;
            final jpeg_d_main_controller jpeg_d_main_controller3;
            final jpeg_d_main_controller jpeg_d_main_controller = jpeg_d_main_controller3 = main;
            ++jpeg_d_main_controller3.iMCU_row_ctr;
        }
        switch (main.context_state) {
            case 2: {
                post_process_data(cinfo, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr], main.rowgroup_ctr, main.rowgroups_avail, output_buf, out_row_ctr, out_rows_avail);
                if (main.rowgroup_ctr[0] < main.rowgroups_avail) {
                    return;
                }
                main.context_state = 0;
                if (out_row_ctr[0] >= out_rows_avail) {
                    return;
                }
            }
            case 0: {
                main.rowgroup_ctr[0] = 0;
                main.rowgroups_avail = cinfo.min_DCT_scaled_size - 1;
                if (main.iMCU_row_ctr == cinfo.total_iMCU_rows) {
                    set_bottom_pointers(cinfo);
                }
                main.context_state = 1;
            }
            case 1: {
                post_process_data(cinfo, main.xbuffer[main.whichptr], main.xbuffer_offset[main.whichptr], main.rowgroup_ctr, main.rowgroups_avail, output_buf, out_row_ctr, out_rows_avail);
                if (main.rowgroup_ctr[0] < main.rowgroups_avail) {
                    return;
                }
                if (main.iMCU_row_ctr == 1) {
                    set_wraparound_pointers(cinfo);
                }
                final jpeg_d_main_controller jpeg_d_main_controller4;
                final jpeg_d_main_controller jpeg_d_main_controller2 = jpeg_d_main_controller4 = main;
                jpeg_d_main_controller4.whichptr ^= 0x1;
                main.buffer_full = false;
                main.rowgroup_ctr[0] = cinfo.min_DCT_scaled_size + 1;
                main.rowgroups_avail = cinfo.min_DCT_scaled_size + 2;
                main.context_state = 2;
                break;
            }
        }
    }
    
    static void process_data_simple_main(final jpeg_decompress_struct cinfo, final byte[][] output_buf, final int[] out_row_ctr, final int out_rows_avail) {
        final jpeg_d_main_controller main = cinfo.main;
        if (!main.buffer_full) {
            int result = 0;
            switch (cinfo.coef.decompress_data) {
                case 0: {
                    result = decompress_data(cinfo, main.buffer, main.buffer_offset);
                    break;
                }
                case 1: {
                    result = decompress_smooth_data(cinfo, main.buffer, main.buffer_offset);
                    break;
                }
                case 2: {
                    result = decompress_onepass(cinfo, main.buffer, main.buffer_offset);
                    break;
                }
                default: {
                    result = 0;
                    break;
                }
            }
            if (result == 0) {
                return;
            }
            main.buffer_full = true;
        }
        final int rowgroups_avail = cinfo.min_DCT_scaled_size;
        post_process_data(cinfo, main.buffer, main.buffer_offset, main.rowgroup_ctr, rowgroups_avail, output_buf, out_row_ctr, out_rows_avail);
        if (main.rowgroup_ctr[0] >= rowgroups_avail) {
            main.buffer_full = false;
            main.rowgroup_ctr[0] = 0;
        }
    }
    
    static int jpeg_read_scanlines(final jpeg_decompress_struct cinfo, final byte[][] scanlines, final int max_lines) {
        if (cinfo.global_state != 205) {
            error();
        }
        if (cinfo.output_scanline >= cinfo.output_height) {
            return 0;
        }
        cinfo.row_ctr[0] = 0;
        switch (cinfo.main.process_data) {
            case 0: {
                process_data_simple_main(cinfo, scanlines, cinfo.row_ctr, max_lines);
                break;
            }
            case 1: {
                process_data_context_main(cinfo, scanlines, cinfo.row_ctr, max_lines);
                break;
            }
            case 2: {
                process_data_crank_post(cinfo, scanlines, cinfo.row_ctr, max_lines);
                break;
            }
            default: {
                error();
                break;
            }
        }
        cinfo.output_scanline += cinfo.row_ctr[0];
        return cinfo.row_ctr[0];
    }
    
    static boolean output_pass_setup(final jpeg_decompress_struct cinfo) {
        if (cinfo.global_state != 204) {
            prepare_for_output_pass(cinfo);
            cinfo.output_scanline = 0;
            cinfo.global_state = 204;
        }
        while (cinfo.master.is_dummy_pass) {
            error();
        }
        cinfo.global_state = (cinfo.raw_data_out ? 206 : 205);
        return true;
    }
    
    static boolean get_dht(final jpeg_decompress_struct cinfo) {
        final byte[] bits = new byte[17];
        final byte[] huffval = new byte[256];
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        length -= 2;
        while (length > 16) {
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            int index = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            bits[0] = 0;
            int count = 0;
            for (int i = 1; i <= 16; ++i) {
                if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                    fill_input_buffer(cinfo);
                }
                bits[i] = cinfo.buffer[cinfo.bytes_offset++];
                count += (bits[i] & 0xFF);
            }
            length -= 17;
            if (count > 256 || count > length) {
                error();
            }
            for (int i = 0; i < count; ++i) {
                if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                    fill_input_buffer(cinfo);
                }
                huffval[i] = cinfo.buffer[cinfo.bytes_offset++];
            }
            length -= count;
            JHUFF_TBL htblptr;
            if ((index & 0x10) != 0x0) {
                index -= 16;
                final JHUFF_TBL[] ac_huff_tbl_ptrs = cinfo.ac_huff_tbl_ptrs;
                final int n = index;
                final JHUFF_TBL jhuff_TBL = new JHUFF_TBL();
                ac_huff_tbl_ptrs[n] = jhuff_TBL;
                htblptr = jhuff_TBL;
            }
            else {
                final JHUFF_TBL[] dc_huff_tbl_ptrs = cinfo.dc_huff_tbl_ptrs;
                final int n2 = index;
                final JHUFF_TBL jhuff_TBL2 = new JHUFF_TBL();
                dc_huff_tbl_ptrs[n2] = jhuff_TBL2;
                htblptr = jhuff_TBL2;
            }
            if (index < 0 || index >= 4) {
                error();
            }
            System.arraycopy(bits, 0, htblptr.bits, 0, bits.length);
            System.arraycopy(huffval, 0, htblptr.huffval, 0, huffval.length);
        }
        if (length != 0) {
            error();
        }
        return true;
    }
    
    static boolean get_dqt(final jpeg_decompress_struct cinfo) {
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        for (length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF), length -= 2; length > 0; length -= 64) {
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            int n = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            final int prec = n >> 4;
            n &= 0xF;
            if (n >= 4) {
                error();
            }
            if (cinfo.quant_tbl_ptrs[n] == null) {
                cinfo.quant_tbl_ptrs[n] = new JQUANT_TBL();
            }
            final JQUANT_TBL quant_ptr = cinfo.quant_tbl_ptrs[n];
            for (int i = 0; i < 64; ++i) {
                int tmp;
                if (prec != 0) {
                    if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                        fill_input_buffer(cinfo);
                    }
                    tmp = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
                    if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                        fill_input_buffer(cinfo);
                    }
                    tmp |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
                }
                else {
                    if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                        fill_input_buffer(cinfo);
                    }
                    tmp = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
                }
                quant_ptr.quantval[JPEGDecoder.jpeg_natural_order[i]] = (short)tmp;
            }
            length -= 65;
            if (prec != 0) {}
        }
        if (length != 0) {
            error();
        }
        return true;
    }
    
    static boolean get_dri(final jpeg_decompress_struct cinfo) {
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        if (length != 4) {
            error();
        }
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int tmp = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        tmp |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        cinfo.restart_interval = tmp;
        return true;
    }
    
    static boolean get_dac(final jpeg_decompress_struct cinfo) {
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        length -= 2;
        while (length > 0) {
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            final int index = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            final int val = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            length -= 2;
            if (index < 0 || index >= 32) {
                error();
            }
            if (index >= 16) {
                cinfo.arith_ac_K[index - 16] = (byte)val;
            }
            else {
                cinfo.arith_dc_L[index] = (byte)(val & 0xF);
                cinfo.arith_dc_U[index] = (byte)(val >> 4);
                if (cinfo.arith_dc_L[index] <= cinfo.arith_dc_U[index]) {
                    continue;
                }
                error();
            }
        }
        if (length != 0) {
            error();
        }
        return true;
    }
    
    static boolean get_sos(final jpeg_decompress_struct cinfo) {
        jpeg_component_info compptr = null;
        if (!cinfo.marker.saw_SOF) {
            error();
        }
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        final int n = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
        if (length != n * 2 + 6 || n < 1 || n > 4) {
            error();
        }
        cinfo.comps_in_scan = n;
        for (int i = 0; i < n; ++i) {
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            final int cc = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            final int c = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            int ci;
            for (ci = 0; ci < cinfo.num_components; ++ci) {
                compptr = cinfo.comp_info[ci];
                if (cc == compptr.component_id) {
                    break;
                }
            }
            if (ci == cinfo.num_components) {
                error();
            }
            cinfo.cur_comp_info[i] = compptr;
            compptr.dc_tbl_no = (c >> 4 & 0xF);
            compptr.ac_tbl_no = (c & 0xF);
        }
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int c2 = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
        cinfo.Ss = c2;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        c2 = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        cinfo.Se = c2;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        c2 = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        cinfo.Ah = (c2 >> 4 & 0xF);
        cinfo.Al = (c2 & 0xF);
        cinfo.marker.next_restart_num = 0;
        ++cinfo.input_scan_number;
        return true;
    }
    
    static boolean get_sof(final jpeg_decompress_struct cinfo, final boolean is_prog, final boolean is_arith) {
        cinfo.progressive_mode = is_prog;
        cinfo.arith_code = is_arith;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        cinfo.data_precision = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        cinfo.image_height = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        cinfo.image_height |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        cinfo.image_width = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        cinfo.image_width |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        cinfo.num_components = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        length -= 8;
        if (cinfo.marker.saw_SOF) {
            error();
        }
        if (cinfo.image_height <= 0 || cinfo.image_width <= 0 || cinfo.num_components <= 0) {
            error();
        }
        if (length != cinfo.num_components * 3) {
            error();
        }
        if (cinfo.comp_info == null) {
            cinfo.comp_info = new jpeg_component_info[cinfo.num_components];
        }
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info[] comp_info = cinfo.comp_info;
            final int n = ci;
            final jpeg_component_info jpeg_component_info = new jpeg_component_info();
            comp_info[n] = jpeg_component_info;
            final jpeg_component_info compptr = jpeg_component_info;
            compptr.component_index = ci;
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            compptr.component_id = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            final int c = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
            compptr.h_samp_factor = (c >> 4 & 0xF);
            compptr.v_samp_factor = (c & 0xF);
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            compptr.quant_tbl_no = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        }
        return cinfo.marker.saw_SOF = true;
    }
    
    static void sep_upsample(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, final int[] in_row_group_ctr, final int in_row_groups_avail, final byte[][] output_buf, final int[] out_row_ctr, int out_rows_avail) {
        final jpeg_upsampler upsample = cinfo.upsample;
        if (upsample.next_row_out >= cinfo.max_v_samp_factor) {
            for (int ci = 0; ci < cinfo.num_components; ++ci) {
                final jpeg_component_info compptr = cinfo.comp_info[ci];
                final int offset = input_buf_offset[ci] + in_row_group_ctr[0] * upsample.rowgroup_height[ci];
                switch (upsample.methods[ci]) {
                    case 0: {
                        noop_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                    case 1: {
                        fullsize_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                    case 2: {
                        h2v1_fancy_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                    case 3: {
                        h2v1_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                    case 4: {
                        h2v2_fancy_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                    case 5: {
                        h2v2_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                    case 6: {
                        int_upsample(cinfo, compptr, input_buf[ci], offset, upsample.color_buf, upsample.color_buf_offset, ci);
                        break;
                    }
                }
            }
            upsample.next_row_out = 0;
        }
        int num_rows = cinfo.max_v_samp_factor - upsample.next_row_out;
        if (num_rows > upsample.rows_to_go) {
            num_rows = upsample.rows_to_go;
        }
        out_rows_avail -= out_row_ctr[0];
        if (num_rows > out_rows_avail) {
            num_rows = out_rows_avail;
        }
        switch (cinfo.cconvert.color_convert) {
            case 0: {
                null_convert(cinfo, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, output_buf, out_row_ctr[0], num_rows);
                break;
            }
            case 1: {
                grayscale_convert(cinfo, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, output_buf, out_row_ctr[0], num_rows);
                break;
            }
            case 2: {
                ycc_rgb_convert(cinfo, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, output_buf, out_row_ctr[0], num_rows);
                break;
            }
            case 3: {
                gray_rgb_convert(cinfo, upsample.color_buf, upsample.color_buf_offset, upsample.next_row_out, output_buf, out_row_ctr[0], num_rows);
                break;
            }
            case 4: {
                error();
                break;
            }
        }
        final int n = 0;
        final int n3 = 0;
        out_row_ctr[n3] += num_rows;
        final jpeg_upsampler jpeg_upsampler3;
        final jpeg_upsampler jpeg_upsampler = jpeg_upsampler3 = upsample;
        jpeg_upsampler3.rows_to_go -= num_rows;
        final jpeg_upsampler jpeg_upsampler4;
        final jpeg_upsampler jpeg_upsampler2 = jpeg_upsampler4 = upsample;
        jpeg_upsampler4.next_row_out += num_rows;
        if (upsample.next_row_out >= cinfo.max_v_samp_factor) {
            final int n2 = 0;
            final int n4 = 0;
            ++in_row_group_ctr[n4];
        }
    }
    
    static void noop_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        output_data_ptr[output_data_index] = null;
    }
    
    static void fullsize_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        output_data_ptr[output_data_index] = input_data;
        output_data_offset[output_data_index] = input_data_offset;
    }
    
    static void h2v1_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        final byte[][] output_data = output_data_ptr[output_data_index];
        output_data_offset[output_data_index] = 0;
        for (int inrow = 0; inrow < cinfo.max_v_samp_factor; ++inrow) {
            final byte[] inptr = input_data[inrow + input_data_offset];
            final byte[] outptr = output_data[inrow];
            int inptr_offset = 0;
            byte invalue;
            for (int outptr_offset = 0, outend = outptr_offset + cinfo.output_width; outptr_offset < outend; outptr[outptr_offset++] = invalue, outptr[outptr_offset++] = invalue) {
                invalue = inptr[inptr_offset++];
            }
        }
    }
    
    static void h2v2_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        final byte[][] output_data = output_data_ptr[output_data_index];
        output_data_offset[output_data_index] = 0;
        int outrow;
        for (int inrow = outrow = 0; outrow < cinfo.max_v_samp_factor; outrow += 2) {
            final byte[] inptr = input_data[inrow + input_data_offset];
            final byte[] outptr = output_data[outrow];
            int inptr_offset = 0;
            byte invalue;
            for (int outptr_offset = 0, outend = outptr_offset + cinfo.output_width; outptr_offset < outend; outptr[outptr_offset++] = invalue, outptr[outptr_offset++] = invalue) {
                invalue = inptr[inptr_offset++];
            }
            jcopy_sample_rows(output_data, outrow, output_data, outrow + 1, 1, cinfo.output_width);
            ++inrow;
        }
    }
    
    static void h2v1_fancy_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        final byte[][] output_data = output_data_ptr[output_data_index];
        output_data_offset[output_data_index] = 0;
        for (int inrow = 0; inrow < cinfo.max_v_samp_factor; ++inrow) {
            final byte[] inptr = input_data[inrow + input_data_offset];
            final byte[] outptr = output_data[inrow];
            int inptr_offset = 0;
            int outptr_offset = 0;
            int invalue = inptr[inptr_offset++] & 0xFF;
            outptr[outptr_offset++] = (byte)invalue;
            outptr[outptr_offset++] = (byte)(invalue * 3 + (inptr[inptr_offset] & 0xFF) + 2 >> 2);
            for (int colctr = compptr.downsampled_width - 2; colctr > 0; --colctr) {
                invalue = (inptr[inptr_offset++] & 0xFF) * 3;
                outptr[outptr_offset++] = (byte)(invalue + (inptr[inptr_offset - 2] & 0xFF) + 1 >> 2);
                outptr[outptr_offset++] = (byte)(invalue + (inptr[inptr_offset] & 0xFF) + 2 >> 2);
            }
            invalue = (inptr[inptr_offset] & 0xFF);
            outptr[outptr_offset++] = (byte)(invalue * 3 + (inptr[inptr_offset - 1] & 0xFF) + 1 >> 2);
            outptr[outptr_offset++] = (byte)invalue;
        }
    }
    
    static void h2v2_fancy_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        final byte[][] output_data = output_data_ptr[output_data_index];
        output_data_offset[output_data_index] = 0;
        int outrow;
        int inrow = outrow = 0;
        while (outrow < cinfo.max_v_samp_factor) {
            for (int v = 0; v < 2; ++v) {
                final byte[] inptr0 = input_data[inrow + input_data_offset];
                byte[] inptr2;
                if (v == 0) {
                    inptr2 = input_data[inrow - 1 + input_data_offset];
                }
                else {
                    inptr2 = input_data[inrow + 1 + input_data_offset];
                }
                final byte[] outptr = output_data[outrow++];
                int inptr0_offset = 0;
                int inptr1_offset = 0;
                int outptr_offset = 0;
                int thiscolsum = (inptr0[inptr0_offset++] & 0xFF) * 3 + (inptr2[inptr1_offset++] & 0xFF);
                int nextcolsum = (inptr0[inptr0_offset++] & 0xFF) * 3 + (inptr2[inptr1_offset++] & 0xFF);
                outptr[outptr_offset++] = (byte)(thiscolsum * 4 + 8 >> 4);
                outptr[outptr_offset++] = (byte)(thiscolsum * 3 + nextcolsum + 7 >> 4);
                int lastcolsum = thiscolsum;
                thiscolsum = nextcolsum;
                for (int colctr = compptr.downsampled_width - 2; colctr > 0; --colctr) {
                    nextcolsum = (inptr0[inptr0_offset++] & 0xFF) * 3 + (inptr2[inptr1_offset++] & 0xFF);
                    outptr[outptr_offset++] = (byte)(thiscolsum * 3 + lastcolsum + 8 >> 4);
                    outptr[outptr_offset++] = (byte)(thiscolsum * 3 + nextcolsum + 7 >> 4);
                    lastcolsum = thiscolsum;
                    thiscolsum = nextcolsum;
                }
                outptr[outptr_offset++] = (byte)(thiscolsum * 3 + lastcolsum + 8 >> 4);
                outptr[outptr_offset++] = (byte)(thiscolsum * 4 + 7 >> 4);
            }
            ++inrow;
        }
    }
    
    static void int_upsample(final jpeg_decompress_struct cinfo, final jpeg_component_info compptr, final byte[][] input_data, final int input_data_offset, final byte[][][] output_data_ptr, final int[] output_data_offset, final int output_data_index) {
        final jpeg_upsampler upsample = cinfo.upsample;
        final byte[][] output_data = output_data_ptr[output_data_index];
        output_data_offset[output_data_index] = 0;
        final int h_expand = upsample.h_expand[compptr.component_index];
        int outrow;
        for (int v_expand = upsample.v_expand[compptr.component_index], inrow = outrow = 0; outrow < cinfo.max_v_samp_factor; outrow += v_expand) {
            final byte[] inptr = input_data[inrow + input_data_offset];
            int inptr_offset = 0;
            final byte[] outptr = output_data[outrow];
            int outptr_offset = 0;
            final int outend = outptr_offset + cinfo.output_width;
            while (outptr_offset < outend) {
                final byte invalue = inptr[inptr_offset++];
                for (int h = h_expand; h > 0; --h) {
                    outptr[outptr_offset++] = invalue;
                }
            }
            if (v_expand > 1) {
                jcopy_sample_rows(output_data, outrow, output_data, outrow + 1, v_expand - 1, cinfo.output_width);
            }
            ++inrow;
        }
    }
    
    static void null_convert(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, int input_row, final byte[][] output_buf, int output_buf_offset, int num_rows) {
        final int num_components = cinfo.num_components;
        final int num_cols = cinfo.output_width;
        while (--num_rows >= 0) {
            for (int ci = 0; ci < num_components; ++ci) {
                final byte[] inptr = input_buf[ci][input_row + input_buf_offset[0]];
                final byte[] outptr = output_buf[output_buf_offset];
                int offset = 0;
                switch (ci) {
                    case 2: {
                        offset = 0;
                        break;
                    }
                    case 1: {
                        offset = 1;
                        break;
                    }
                    case 0: {
                        offset = 2;
                        break;
                    }
                }
                int outptr_offset = offset;
                int inptr_offset = 0;
                for (int count = num_cols; count > 0; --count) {
                    outptr[outptr_offset] = inptr[inptr_offset++];
                    outptr_offset += num_components;
                }
            }
            ++input_row;
            ++output_buf_offset;
        }
    }
    
    static void grayscale_convert(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, final int input_row, final byte[][] output_buf, final int output_buf_offset, final int num_rows) {
        jcopy_sample_rows(input_buf[0], input_row + input_buf_offset[0], output_buf, output_buf_offset, num_rows, cinfo.output_width);
    }
    
    static void gray_rgb_convert(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, int input_row, final byte[][] output_buf, int output_buf_offset, int num_rows) {
        final int num_cols = cinfo.output_width;
        while (--num_rows >= 0) {
            final byte[] inptr = input_buf[0][input_row++ + input_buf_offset[0]];
            final byte[] outptr = output_buf[output_buf_offset++];
            int outptr_offset = 0;
            for (int col = 0; col < num_cols; ++col) {
                final byte[] array = outptr;
                final int n = 2 + outptr_offset;
                final byte[] array2 = outptr;
                final int n2 = 1 + outptr_offset;
                final byte[] array3 = outptr;
                final int n3 = 0 + outptr_offset;
                final byte b = inptr[col];
                array3[n3] = b;
                array[n] = (array2[n2] = b);
                outptr_offset += 3;
            }
        }
    }
    
    static void ycc_rgb_convert(final jpeg_decompress_struct cinfo, final byte[][][] input_buf, final int[] input_buf_offset, int input_row, final byte[][] output_buf, int output_buf_offset, int num_rows) {
        final jpeg_color_deconverter cconvert = cinfo.cconvert;
        final int num_cols = cinfo.output_width;
        final byte[] range_limit = cinfo.sample_range_limit;
        final int range_limit_offset = cinfo.sample_range_limit_offset;
        final int[] Crrtab = cconvert.Cr_r_tab;
        final int[] Cbbtab = cconvert.Cb_b_tab;
        final int[] Crgtab = cconvert.Cr_g_tab;
        final int[] Cbgtab = cconvert.Cb_g_tab;
        while (--num_rows >= 0) {
            final byte[] inptr0 = input_buf[0][input_row + input_buf_offset[0]];
            final byte[] inptr2 = input_buf[1][input_row + input_buf_offset[1]];
            final byte[] inptr3 = input_buf[2][input_row + input_buf_offset[2]];
            ++input_row;
            final byte[] outptr = output_buf[output_buf_offset++];
            int outptr_offset = 0;
            for (int col = 0; col < num_cols; ++col) {
                final int y = inptr0[col] & 0xFF;
                final int cb = inptr2[col] & 0xFF;
                final int cr = inptr3[col] & 0xFF;
                outptr[outptr_offset + 2] = range_limit[y + Crrtab[cr] + range_limit_offset];
                outptr[outptr_offset + 1] = range_limit[y + (Cbgtab[cb] + Crgtab[cr] >> 16) + range_limit_offset];
                outptr[outptr_offset + 0] = range_limit[y + Cbbtab[cb] + range_limit_offset];
                outptr_offset += 3;
            }
        }
    }
    
    static boolean process_APPn(final int n, final jpeg_decompress_struct cinfo) {
        if (n == 0 || n == 14) {
            return get_interesting_appn(cinfo);
        }
        return skip_variable(cinfo);
    }
    
    static boolean process_COM(final jpeg_decompress_struct cinfo) {
        return skip_variable(cinfo);
    }
    
    static void skip_input_data(final jpeg_decompress_struct cinfo, int num_bytes) {
        if (num_bytes > 0) {
            while (num_bytes > cinfo.bytes_in_buffer - cinfo.bytes_offset) {
                num_bytes -= cinfo.bytes_in_buffer - cinfo.bytes_offset;
                if (!fill_input_buffer(cinfo)) {
                    error();
                }
            }
            cinfo.bytes_offset += num_bytes;
        }
    }
    
    static boolean skip_variable(final jpeg_decompress_struct cinfo) {
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        length -= 2;
        if (length > 0) {
            skip_input_data(cinfo, length);
        }
        return true;
    }
    
    static boolean get_interesting_appn(final jpeg_decompress_struct cinfo) {
        final byte[] b = new byte[14];
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        int length = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF) << 8;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        length |= (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
        length -= 2;
        int numtoread;
        if (length >= 14) {
            numtoread = 14;
        }
        else if (length > 0) {
            numtoread = length;
        }
        else {
            numtoread = 0;
        }
        for (int i = 0; i < numtoread; ++i) {
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            b[i] = cinfo.buffer[cinfo.bytes_offset++];
        }
        length -= numtoread;
        switch (cinfo.unread_marker) {
            case 224: {
                examine_app0(cinfo, b, numtoread, length);
                break;
            }
            case 238: {
                examine_app14(cinfo, b, numtoread, length);
                break;
            }
            default: {
                error();
                break;
            }
        }
        if (length > 0) {
            skip_input_data(cinfo, length);
        }
        return true;
    }
    
    static void examine_app0(final jpeg_decompress_struct cinfo, final byte[] data, final int datalen, final int remaining) {
        int totallen = datalen + remaining;
        if (datalen >= 14 && (data[0] & 0xFF) == 0x4A && (data[1] & 0xFF) == 0x46 && (data[2] & 0xFF) == 0x49 && (data[3] & 0xFF) == 0x46 && (data[4] & 0xFF) == 0x0) {
            cinfo.saw_JFIF_marker = true;
            cinfo.JFIF_major_version = data[5];
            cinfo.JFIF_minor_version = (byte)(data[6] & 0xFF);
            cinfo.density_unit = (byte)(data[7] & 0xFF);
            cinfo.X_density = (short)(((data[8] & 0xFF) << 8) + (data[9] & 0xFF));
            cinfo.Y_density = (short)(((data[10] & 0xFF) << 8) + (data[11] & 0xFF));
            if (cinfo.JFIF_major_version != 1) {}
            if (((data[12] & 0xFF) | (data[13] & 0xFF)) != 0x0) {}
            totallen -= 14;
            if (totallen != (data[12] & 0xFF) * (data[13] & 0xFF) * 3) {}
        }
        else if (datalen >= 6 && (data[0] & 0xFF) == 0x4A && (data[1] & 0xFF) == 0x46 && (data[2] & 0xFF) == 0x58 && (data[3] & 0xFF) == 0x58 && (data[4] & 0xFF) == 0x0) {
            switch (data[5] & 0xFF) {
            }
        }
    }
    
    static void examine_app14(final jpeg_decompress_struct cinfo, final byte[] data, final int datalen, final int remaining) {
        if (datalen >= 12 && (data[0] & 0xFF) == 0x41 && (data[1] & 0xFF) == 0x64 && (data[2] & 0xFF) == 0x6F && (data[3] & 0xFF) == 0x62 && (data[4] & 0xFF) == 0x65) {
            final int transform = data[11] & 0xFF;
            cinfo.saw_Adobe_marker = true;
            cinfo.Adobe_transform = (byte)transform;
        }
    }
    
    static boolean get_soi(final jpeg_decompress_struct cinfo) {
        if (cinfo.marker.saw_SOI) {
            error();
        }
        for (int i = 0; i < 16; ++i) {
            cinfo.arith_dc_L[i] = 0;
            cinfo.arith_dc_U[i] = 1;
            cinfo.arith_ac_K[i] = 5;
        }
        cinfo.restart_interval = 0;
        cinfo.jpeg_color_space = 0;
        cinfo.CCIR601_sampling = false;
        cinfo.saw_JFIF_marker = false;
        cinfo.JFIF_major_version = 1;
        cinfo.JFIF_minor_version = 1;
        cinfo.density_unit = 0;
        cinfo.X_density = 1;
        cinfo.Y_density = 1;
        cinfo.saw_Adobe_marker = false;
        cinfo.Adobe_transform = 0;
        return cinfo.marker.saw_SOI = true;
    }
    
    static void jinit_input_controller(final jpeg_decompress_struct cinfo) {
        final jpeg_input_controller inputctl2 = new jpeg_input_controller();
        cinfo.inputctl = inputctl2;
        final jpeg_input_controller inputctl3 = inputctl2;
        inputctl3.has_multiple_scans = false;
        inputctl3.eoi_reached = false;
        inputctl3.inheaders = true;
    }
    
    static void reset_marker_reader(final jpeg_decompress_struct cinfo) {
        final jpeg_marker_reader marker = cinfo.marker;
        cinfo.comp_info = null;
        cinfo.input_scan_number = 0;
        cinfo.unread_marker = 0;
        marker.saw_SOI = false;
        marker.saw_SOF = false;
        marker.discarded_bytes = 0;
    }
    
    static void reset_input_controller(final jpeg_decompress_struct cinfo) {
        final jpeg_input_controller inputctl = cinfo.inputctl;
        inputctl.has_multiple_scans = false;
        inputctl.eoi_reached = false;
        inputctl.inheaders = true;
        reset_marker_reader(cinfo);
        cinfo.coef_bits = null;
    }
    
    static void finish_output_pass(final jpeg_decompress_struct cinfo) {
        final jpeg_decomp_master master = cinfo.master;
        if (cinfo.quantize_colors) {
            error(20);
        }
        final jpeg_decomp_master jpeg_decomp_master2;
        final jpeg_decomp_master jpeg_decomp_master = jpeg_decomp_master2 = master;
        ++jpeg_decomp_master2.pass_number;
    }
    
    static void jpeg_destroy(final jpeg_decompress_struct cinfo) {
        cinfo.global_state = 0;
    }
    
    static void jpeg_destroy_decompress(final jpeg_decompress_struct cinfo) {
        jpeg_destroy(cinfo);
    }
    
    static boolean jpeg_input_complete(final jpeg_decompress_struct cinfo) {
        if (cinfo.global_state < 200 || cinfo.global_state > 210) {
            error();
        }
        return cinfo.inputctl.eoi_reached;
    }
    
    static boolean jpeg_start_output(final jpeg_decompress_struct cinfo, int scan_number) {
        if (cinfo.global_state != 207 && cinfo.global_state != 204) {
            error();
        }
        if (scan_number <= 0) {
            scan_number = 1;
        }
        if (cinfo.inputctl.eoi_reached && scan_number > cinfo.input_scan_number) {
            scan_number = cinfo.input_scan_number;
        }
        cinfo.output_scan_number = scan_number;
        return output_pass_setup(cinfo);
    }
    
    static boolean jpeg_finish_output(final jpeg_decompress_struct cinfo) {
        if ((cinfo.global_state == 205 || cinfo.global_state == 206) && cinfo.buffered_image) {
            finish_output_pass(cinfo);
            cinfo.global_state = 208;
        }
        else if (cinfo.global_state != 208) {
            error();
        }
        while (cinfo.input_scan_number <= cinfo.output_scan_number && !cinfo.inputctl.eoi_reached) {
            if (consume_input(cinfo) == 0) {
                return false;
            }
        }
        cinfo.global_state = 207;
        return true;
    }
    
    static boolean jpeg_finish_decompress(final jpeg_decompress_struct cinfo) {
        if ((cinfo.global_state == 205 || cinfo.global_state == 206) && !cinfo.buffered_image) {
            if (cinfo.output_scanline < cinfo.output_height) {
                error();
            }
            finish_output_pass(cinfo);
            cinfo.global_state = 210;
        }
        else if (cinfo.global_state == 207) {
            cinfo.global_state = 210;
        }
        else if (cinfo.global_state != 210) {
            error();
        }
        while (!cinfo.inputctl.eoi_reached) {
            if (consume_input(cinfo) == 0) {
                return false;
            }
        }
        jpeg_abort(cinfo);
        return true;
    }
    
    static int jpeg_read_header(final jpeg_decompress_struct cinfo, final boolean require_image) {
        if (cinfo.global_state != 200 && cinfo.global_state != 201) {
            error();
        }
        int retcode = jpeg_consume_input(cinfo);
        switch (retcode) {
            case 1: {
                retcode = 1;
                break;
            }
            case 2: {
                if (require_image) {
                    error();
                }
                jpeg_abort(cinfo);
                retcode = 2;
                break;
            }
        }
        return retcode;
    }
    
    static int dummy_consume_data(final jpeg_decompress_struct cinfo) {
        return 0;
    }
    
    static int consume_data(final jpeg_decompress_struct cinfo) {
        final jpeg_d_coef_controller coef = cinfo.coef;
        for (int yoffset = coef.MCU_vert_offset; yoffset < coef.MCU_rows_per_iMCU_row; ++yoffset) {
            for (int MCU_col_num = coef.MCU_ctr; MCU_col_num < cinfo.MCUs_per_row; ++MCU_col_num) {
                int blkn = 0;
                for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                    final jpeg_component_info compptr = cinfo.cur_comp_info[ci];
                    final int start_col = MCU_col_num * compptr.MCU_width;
                    for (int yindex = 0; yindex < compptr.MCU_height; ++yindex) {
                        final short[][] buffer_ptr = coef.whole_image[compptr.component_index][yindex + yoffset + cinfo.input_iMCU_row * compptr.v_samp_factor];
                        int buffer_ptr_offset = start_col;
                        for (int xindex = 0; xindex < compptr.MCU_width; ++xindex) {
                            coef.MCU_buffer[blkn++] = buffer_ptr[buffer_ptr_offset++];
                        }
                    }
                }
                if (!cinfo.entropy.decode_mcu(cinfo, coef.MCU_buffer)) {
                    coef.MCU_vert_offset = yoffset;
                    coef.MCU_ctr = MCU_col_num;
                    return 0;
                }
            }
            coef.MCU_ctr = 0;
        }
        if (++cinfo.input_iMCU_row < cinfo.total_iMCU_rows) {
            coef.start_iMCU_row(cinfo);
            return 3;
        }
        finish_input_pass(cinfo);
        return 4;
    }
    
    static int consume_input(final jpeg_decompress_struct cinfo) {
        Label_0088: {
            switch (cinfo.inputctl.consume_input) {
                case 1: {
                    switch (cinfo.coef.consume_data) {
                        case 0: {
                            return consume_data(cinfo);
                        }
                        case 1: {
                            return dummy_consume_data(cinfo);
                        }
                        default: {
                            error();
                            break Label_0088;
                        }
                    }
                    break;
                }
                case 0: {
                    return consume_markers(cinfo);
                }
                default: {
                    error();
                    break;
                }
            }
        }
        return 0;
    }
    
    static boolean fill_input_buffer(final jpeg_decompress_struct cinfo) {
        try {
            final InputStream inputStream = cinfo.inputStream;
            int nbytes = inputStream.read(cinfo.buffer);
            if (nbytes <= 0) {
                if (cinfo.start_of_file) {
                    error();
                }
                cinfo.buffer[0] = -1;
                cinfo.buffer[1] = -39;
                nbytes = 2;
            }
            cinfo.bytes_in_buffer = nbytes;
            cinfo.bytes_offset = 0;
            cinfo.start_of_file = false;
        }
        catch (IOException e) {
            error(39);
            return false;
        }
        return true;
    }
    
    static boolean first_marker(final jpeg_decompress_struct cinfo) {
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        final int c = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
        if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
            fill_input_buffer(cinfo);
        }
        final int c2 = cinfo.buffer[cinfo.bytes_offset++] & 0xFF;
        if (c != 255 || c2 != 216) {
            error();
        }
        cinfo.unread_marker = c2;
        return true;
    }
    
    static boolean next_marker(final jpeg_decompress_struct cinfo) {
        int c;
        while (true) {
            if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                fill_input_buffer(cinfo);
            }
            for (c = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF); c != 255; c = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF)) {
                final jpeg_marker_reader marker3;
                final jpeg_marker_reader marker = marker3 = cinfo.marker;
                ++marker3.discarded_bytes;
                if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                    fill_input_buffer(cinfo);
                }
            }
            do {
                if (cinfo.bytes_offset == cinfo.bytes_in_buffer) {
                    fill_input_buffer(cinfo);
                }
                c = (cinfo.buffer[cinfo.bytes_offset++] & 0xFF);
            } while (c == 255);
            if (c != 0) {
                break;
            }
            final jpeg_marker_reader marker4;
            final jpeg_marker_reader marker2 = marker4 = cinfo.marker;
            marker4.discarded_bytes += 2;
        }
        if (cinfo.marker.discarded_bytes != 0) {
            cinfo.marker.discarded_bytes = 0;
        }
        cinfo.unread_marker = c;
        return true;
    }
    
    static int read_markers(final jpeg_decompress_struct cinfo) {
        while (true) {
            if (cinfo.unread_marker == 0) {
                if (!cinfo.marker.saw_SOI) {
                    if (!first_marker(cinfo)) {
                        return 0;
                    }
                }
                else if (!next_marker(cinfo)) {
                    return 0;
                }
            }
            switch (cinfo.unread_marker) {
                case 216: {
                    if (!get_soi(cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 192:
                case 193: {
                    if (!get_sof(cinfo, false, false)) {
                        return 0;
                    }
                    break;
                }
                case 194: {
                    if (!get_sof(cinfo, true, false)) {
                        return 0;
                    }
                    break;
                }
                case 201: {
                    if (!get_sof(cinfo, false, true)) {
                        return 0;
                    }
                    break;
                }
                case 202: {
                    if (!get_sof(cinfo, true, true)) {
                        return 0;
                    }
                    break;
                }
                case 195:
                case 197:
                case 198:
                case 199:
                case 200:
                case 203:
                case 205:
                case 206:
                case 207: {
                    error();
                    break;
                }
                case 218: {
                    if (!get_sos(cinfo)) {
                        return 0;
                    }
                    cinfo.unread_marker = 0;
                    return 1;
                }
                case 217: {
                    cinfo.unread_marker = 0;
                    return 2;
                }
                case 204: {
                    if (!get_dac(cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 196: {
                    if (!get_dht(cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 219: {
                    if (!get_dqt(cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 221: {
                    if (!get_dri(cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 224:
                case 225:
                case 226:
                case 227:
                case 228:
                case 229:
                case 230:
                case 231:
                case 232:
                case 233:
                case 234:
                case 235:
                case 236:
                case 237:
                case 238:
                case 239: {
                    if (!process_APPn(cinfo.unread_marker - 224, cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 254: {
                    if (!process_COM(cinfo)) {
                        return 0;
                    }
                    break;
                }
                case 1:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215: {
                    break;
                }
                case 220: {
                    if (!skip_variable(cinfo)) {
                        return 0;
                    }
                    break;
                }
                default: {
                    error();
                    break;
                }
            }
            cinfo.unread_marker = 0;
        }
    }
    
    static long jdiv_round_up(final long a, final long b) {
        return (a + b - 1L) / b;
    }
    
    static void initial_setup(final jpeg_decompress_struct cinfo) {
        if (cinfo.image_height > 65500 || cinfo.image_width > 65500) {
            error();
        }
        if (cinfo.data_precision != 8) {
            error(" [data precision=" + cinfo.data_precision);
        }
        if (cinfo.num_components > 10) {
            error();
        }
        cinfo.max_h_samp_factor = 1;
        cinfo.max_v_samp_factor = 1;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            if (compptr.h_samp_factor <= 0 || compptr.h_samp_factor > 4 || compptr.v_samp_factor <= 0 || compptr.v_samp_factor > 4) {
                error();
            }
            cinfo.max_h_samp_factor = Math.max(cinfo.max_h_samp_factor, compptr.h_samp_factor);
            cinfo.max_v_samp_factor = Math.max(cinfo.max_v_samp_factor, compptr.v_samp_factor);
        }
        cinfo.min_DCT_scaled_size = 8;
        for (int ci = 0; ci < cinfo.num_components; ++ci) {
            final jpeg_component_info compptr = cinfo.comp_info[ci];
            compptr.DCT_scaled_size = 8;
            compptr.width_in_blocks = (int)jdiv_round_up(cinfo.image_width * (long)compptr.h_samp_factor, cinfo.max_h_samp_factor * 8);
            compptr.height_in_blocks = (int)jdiv_round_up(cinfo.image_height * (long)compptr.v_samp_factor, cinfo.max_v_samp_factor * 8);
            compptr.downsampled_width = (int)jdiv_round_up(cinfo.image_width * (long)compptr.h_samp_factor, cinfo.max_h_samp_factor);
            compptr.downsampled_height = (int)jdiv_round_up(cinfo.image_height * (long)compptr.v_samp_factor, cinfo.max_v_samp_factor);
            compptr.component_needed = true;
            compptr.quant_table = null;
        }
        cinfo.total_iMCU_rows = (int)jdiv_round_up(cinfo.image_height, cinfo.max_v_samp_factor * 8);
        if (cinfo.comps_in_scan < cinfo.num_components || cinfo.progressive_mode) {
            cinfo.inputctl.has_multiple_scans = true;
        }
        else {
            cinfo.inputctl.has_multiple_scans = false;
        }
    }
    
    static void per_scan_setup(final jpeg_decompress_struct cinfo) {
        int tmp = 0;
        if (cinfo.comps_in_scan == 1) {
            final jpeg_component_info compptr = cinfo.cur_comp_info[0];
            cinfo.MCUs_per_row = compptr.width_in_blocks;
            cinfo.MCU_rows_in_scan = compptr.height_in_blocks;
            compptr.MCU_width = 1;
            compptr.MCU_height = 1;
            compptr.MCU_blocks = 1;
            compptr.MCU_sample_width = compptr.DCT_scaled_size;
            compptr.last_col_width = 1;
            tmp = compptr.height_in_blocks % compptr.v_samp_factor;
            if (tmp == 0) {
                tmp = compptr.v_samp_factor;
            }
            compptr.last_row_height = tmp;
            cinfo.blocks_in_MCU = 1;
            cinfo.MCU_membership[0] = 0;
        }
        else {
            if (cinfo.comps_in_scan <= 0 || cinfo.comps_in_scan > 4) {
                error();
            }
            cinfo.MCUs_per_row = (int)jdiv_round_up(cinfo.image_width, cinfo.max_h_samp_factor * 8);
            cinfo.MCU_rows_in_scan = (int)jdiv_round_up(cinfo.image_height, cinfo.max_v_samp_factor * 8);
            cinfo.blocks_in_MCU = 0;
            for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                final jpeg_component_info compptr2 = cinfo.cur_comp_info[ci];
                compptr2.MCU_width = compptr2.h_samp_factor;
                compptr2.MCU_height = compptr2.v_samp_factor;
                compptr2.MCU_blocks = compptr2.MCU_width * compptr2.MCU_height;
                compptr2.MCU_sample_width = compptr2.MCU_width * compptr2.DCT_scaled_size;
                tmp = compptr2.width_in_blocks % compptr2.MCU_width;
                if (tmp == 0) {
                    tmp = compptr2.MCU_width;
                }
                compptr2.last_col_width = tmp;
                tmp = compptr2.height_in_blocks % compptr2.MCU_height;
                if (tmp == 0) {
                    tmp = compptr2.MCU_height;
                }
                compptr2.last_row_height = tmp;
                int mcublks = compptr2.MCU_blocks;
                if (cinfo.blocks_in_MCU + mcublks > 10) {
                    error();
                }
                while (mcublks-- > 0) {
                    cinfo.MCU_membership[cinfo.blocks_in_MCU++] = ci;
                }
            }
        }
    }
    
    static void latch_quant_tables(final jpeg_decompress_struct cinfo) {
        for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
            final jpeg_component_info compptr = cinfo.cur_comp_info[ci];
            if (compptr.quant_table == null) {
                final int qtblno = compptr.quant_tbl_no;
                if (qtblno < 0 || qtblno >= 4 || cinfo.quant_tbl_ptrs[qtblno] == null) {
                    error();
                }
                final JQUANT_TBL qtbl = new JQUANT_TBL();
                System.arraycopy(cinfo.quant_tbl_ptrs[qtblno].quantval, 0, qtbl.quantval, 0, qtbl.quantval.length);
                qtbl.sent_table = cinfo.quant_tbl_ptrs[qtblno].sent_table;
                compptr.quant_table = qtbl;
            }
        }
    }
    
    static void jpeg_make_d_derived_tbl(final jpeg_decompress_struct cinfo, final boolean isDC, final int tblno, final d_derived_tbl dtbl) {
        int i = 0;
        final byte[] huffsize = new byte[257];
        final int[] huffcode = new int[257];
        if (tblno < 0 || tblno >= 4) {
            error();
        }
        final JHUFF_TBL htbl = isDC ? cinfo.dc_huff_tbl_ptrs[tblno] : cinfo.ac_huff_tbl_ptrs[tblno];
        if (htbl == null) {
            error();
        }
        dtbl.pub = htbl;
        int p = 0;
        for (int l = 1; l <= 16; ++l) {
            i = (htbl.bits[l] & 0xFF);
            if (i < 0 || p + i > 256) {
                error();
            }
            while (i-- != 0) {
                huffsize[p++] = (byte)l;
            }
        }
        huffsize[p] = 0;
        final int numsymbols = p;
        int code = 0;
        int si = huffsize[0];
        p = 0;
        while (huffsize[p] != 0) {
            while (huffsize[p] == si) {
                huffcode[p++] = code;
                ++code;
            }
            if (code >= 1 << si) {
                error();
            }
            code <<= 1;
            ++si;
        }
        p = 0;
        for (int j = 1; j <= 16; ++j) {
            if ((htbl.bits[j] & 0xFF) != 0x0) {
                dtbl.valoffset[j] = p - huffcode[p];
                p += (htbl.bits[j] & 0xFF);
                dtbl.maxcode[j] = huffcode[p - 1];
            }
            else {
                dtbl.maxcode[j] = -1;
            }
        }
        dtbl.maxcode[17] = 1048575;
        for (int k = 0; k < dtbl.look_nbits.length; ++k) {
            dtbl.look_nbits[k] = 0;
        }
        p = 0;
        for (int j = 1; j <= 8; ++j) {
            for (i = 1; i <= (htbl.bits[j] & 0xFF); ++i, ++p) {
                int lookbits = huffcode[p] << 8 - j;
                for (int ctr = 1 << 8 - j; ctr > 0; --ctr) {
                    dtbl.look_nbits[lookbits] = j;
                    dtbl.look_sym[lookbits] = htbl.huffval[p];
                    ++lookbits;
                }
            }
        }
        if (isDC) {
            for (i = 0; i < numsymbols; ++i) {
                final int sym = htbl.huffval[i] & 0xFF;
                if (sym < 0 || sym > 15) {
                    error();
                }
            }
        }
    }
    
    static void start_input_pass(final jpeg_decompress_struct cinfo) {
        per_scan_setup(cinfo);
        latch_quant_tables(cinfo);
        cinfo.entropy.start_pass(cinfo);
        cinfo.coef.start_input_pass(cinfo);
        cinfo.inputctl.consume_input = 1;
    }
    
    static void finish_input_pass(final jpeg_decompress_struct cinfo) {
        cinfo.inputctl.consume_input = 0;
    }
    
    static int consume_markers(final jpeg_decompress_struct cinfo) {
        final jpeg_input_controller inputctl = cinfo.inputctl;
        if (inputctl.eoi_reached) {
            return 2;
        }
        final int val = read_markers(cinfo);
        switch (val) {
            case 1: {
                if (inputctl.inheaders) {
                    initial_setup(cinfo);
                    inputctl.inheaders = false;
                    break;
                }
                if (!inputctl.has_multiple_scans) {
                    error();
                }
                start_input_pass(cinfo);
                break;
            }
            case 2: {
                inputctl.eoi_reached = true;
                if (inputctl.inheaders) {
                    if (cinfo.marker.saw_SOF) {
                        error();
                        break;
                    }
                    break;
                }
                else {
                    if (cinfo.output_scan_number > cinfo.input_scan_number) {
                        cinfo.output_scan_number = cinfo.input_scan_number;
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return val;
    }
    
    static void default_decompress_parms(final jpeg_decompress_struct cinfo) {
        switch (cinfo.num_components) {
            case 1: {
                cinfo.jpeg_color_space = 1;
                cinfo.out_color_space = 1;
                break;
            }
            case 3: {
                if (cinfo.saw_JFIF_marker) {
                    cinfo.jpeg_color_space = 3;
                }
                else if (cinfo.saw_Adobe_marker) {
                    switch (cinfo.Adobe_transform) {
                        case 0: {
                            cinfo.jpeg_color_space = 2;
                            break;
                        }
                        case 1: {
                            cinfo.jpeg_color_space = 3;
                            break;
                        }
                        default: {
                            cinfo.jpeg_color_space = 3;
                            break;
                        }
                    }
                }
                else {
                    final int cid0 = cinfo.comp_info[0].component_id;
                    final int cid2 = cinfo.comp_info[1].component_id;
                    final int cid3 = cinfo.comp_info[2].component_id;
                    if (cid0 == 1 && cid2 == 2 && cid3 == 3) {
                        cinfo.jpeg_color_space = 3;
                    }
                    else if (cid0 == 82 && cid2 == 71 && cid3 == 66) {
                        cinfo.jpeg_color_space = 2;
                    }
                    else {
                        cinfo.jpeg_color_space = 3;
                    }
                }
                cinfo.out_color_space = 2;
                break;
            }
            case 4: {
                if (cinfo.saw_Adobe_marker) {
                    switch (cinfo.Adobe_transform) {
                        case 0: {
                            cinfo.jpeg_color_space = 4;
                            break;
                        }
                        case 2: {
                            cinfo.jpeg_color_space = 5;
                            break;
                        }
                        default: {
                            cinfo.jpeg_color_space = 5;
                            break;
                        }
                    }
                }
                else {
                    cinfo.jpeg_color_space = 4;
                }
                cinfo.out_color_space = 4;
                break;
            }
            default: {
                cinfo.jpeg_color_space = 0;
                cinfo.out_color_space = 0;
                break;
            }
        }
        cinfo.scale_num = 1;
        cinfo.scale_denom = 1;
        cinfo.output_gamma = 1.0;
        cinfo.buffered_image = false;
        cinfo.raw_data_out = false;
        cinfo.dct_method = 0;
        cinfo.do_fancy_upsampling = true;
        cinfo.do_block_smoothing = true;
        cinfo.quantize_colors = false;
        cinfo.dither_mode = 2;
        cinfo.two_pass_quantize = true;
        cinfo.desired_number_of_colors = 256;
        cinfo.colormap = null;
        cinfo.enable_1pass_quant = false;
        cinfo.enable_external_quant = false;
        cinfo.enable_2pass_quant = false;
    }
    
    static void init_source(final jpeg_decompress_struct cinfo) {
        cinfo.buffer = new byte[4096];
        cinfo.bytes_in_buffer = 0;
        cinfo.bytes_offset = 0;
        cinfo.start_of_file = true;
    }
    
    static int jpeg_consume_input(final jpeg_decompress_struct cinfo) {
        int retcode = 0;
        switch (cinfo.global_state) {
            case 200: {
                reset_input_controller(cinfo);
                init_source(cinfo);
                cinfo.global_state = 201;
            }
            case 201: {
                retcode = consume_input(cinfo);
                if (retcode == 1) {
                    default_decompress_parms(cinfo);
                    cinfo.global_state = 202;
                    break;
                }
                break;
            }
            case 202: {
                retcode = 1;
                break;
            }
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 210: {
                retcode = consume_input(cinfo);
                break;
            }
            default: {
                error();
                break;
            }
        }
        return retcode;
    }
    
    static void jpeg_abort(final jpeg_decompress_struct cinfo) {
        if (cinfo.is_decompressor) {
            cinfo.global_state = 200;
        }
        else {
            cinfo.global_state = 100;
        }
    }
    
    static boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] buffer = new byte[2];
            stream.read(buffer);
            stream.unread(buffer);
            return (buffer[0] & 0xFF) == 0xFF && (buffer[1] & 0xFF) == 0xD8;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    static ImageData[] loadFromByteStream(final InputStream inputStream, final ImageLoader loader) {
        final jpeg_decompress_struct cinfo = new jpeg_decompress_struct();
        cinfo.inputStream = inputStream;
        jpeg_create_decompress(cinfo);
        jpeg_read_header(cinfo, true);
        cinfo.buffered_image = (cinfo.progressive_mode && loader.hasListeners());
        jpeg_start_decompress(cinfo);
        PaletteData palette = null;
        switch (cinfo.out_color_space) {
            case 2: {
                palette = new PaletteData(255, 65280, 16711680);
                break;
            }
            case 1: {
                final RGB[] colors = new RGB[256];
                for (int i = 0; i < colors.length; ++i) {
                    colors[i] = new RGB(i, i, i);
                }
                palette = new PaletteData(colors);
                break;
            }
            default: {
                error();
                break;
            }
        }
        final int scanlinePad = 4;
        final int row_stride = ((cinfo.output_width * cinfo.out_color_components * 8 + 7) / 8 + 3) / 4 * 4;
        final byte[][] buffer = new byte[1][row_stride];
        final byte[] data = new byte[row_stride * cinfo.output_height];
        final ImageData imageData = ImageData.internal_new(cinfo.output_width, cinfo.output_height, palette.isDirect ? 24 : 8, palette, 4, data, 0, (byte[])null, (byte[])null, -1, -1, 4, 0, 0, 0, 0);
        if (cinfo.buffered_image) {
            boolean done;
            do {
                final int incrementCount = cinfo.input_scan_number - 1;
                jpeg_start_output(cinfo, cinfo.input_scan_number);
                while (cinfo.output_scanline < cinfo.output_height) {
                    final int offset = row_stride * cinfo.output_scanline;
                    jpeg_read_scanlines(cinfo, buffer, 1);
                    System.arraycopy(buffer[0], 0, data, offset, row_stride);
                }
                jpeg_finish_output(cinfo);
                loader.notifyListeners(new ImageLoaderEvent(loader, (ImageData)imageData.clone(), incrementCount, done = jpeg_input_complete(cinfo)));
            } while (!done);
        }
        else {
            while (cinfo.output_scanline < cinfo.output_height) {
                final int offset2 = row_stride * cinfo.output_scanline;
                jpeg_read_scanlines(cinfo, buffer, 1);
                System.arraycopy(buffer[0], 0, data, offset2, row_stride);
            }
        }
        jpeg_finish_decompress(cinfo);
        jpeg_destroy_decompress(cinfo);
        return new ImageData[] { imageData };
    }
    
    static {
        JPEGDecoder.extend_test = new int[] { 0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384 };
        JPEGDecoder.extend_offset = new int[] { 0, -1, -3, -7, -15, -31, -63, -127, -255, -511, -1023, -2047, -4095, -8191, -16383, -32767 };
        JPEGDecoder.jpeg_natural_order = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
    }
    
    static final class JQUANT_TBL
    {
        short[] quantval;
        boolean sent_table;
        
        JQUANT_TBL() {
            this.quantval = new short[64];
        }
    }
    
    static final class JHUFF_TBL
    {
        byte[] bits;
        byte[] huffval;
        boolean sent_table;
        
        JHUFF_TBL() {
            this.bits = new byte[17];
            this.huffval = new byte[256];
        }
    }
    
    static final class bitread_perm_state
    {
        int get_buffer;
        int bits_left;
    }
    
    static final class bitread_working_state
    {
        byte[] buffer;
        int bytes_offset;
        int bytes_in_buffer;
        int get_buffer;
        int bits_left;
        jpeg_decompress_struct cinfo;
    }
    
    static final class savable_state
    {
        int EOBRUN;
        int[] last_dc_val;
        
        savable_state() {
            this.last_dc_val = new int[4];
        }
    }
    
    static final class d_derived_tbl
    {
        int[] maxcode;
        int[] valoffset;
        JHUFF_TBL pub;
        int[] look_nbits;
        byte[] look_sym;
        
        d_derived_tbl() {
            this.maxcode = new int[18];
            this.valoffset = new int[17];
            this.look_nbits = new int[256];
            this.look_sym = new byte[256];
        }
    }
    
    static final class jpeg_d_coef_controller
    {
        int consume_data;
        int decompress_data;
        short[][][] coef_arrays;
        int MCU_ctr;
        int MCU_vert_offset;
        int MCU_rows_per_iMCU_row;
        short[][] MCU_buffer;
        short[][][][] whole_image;
        int[] coef_bits_latch;
        short[] workspace;
        
        jpeg_d_coef_controller() {
            this.MCU_buffer = new short[10][];
            this.whole_image = new short[10][][][];
        }
        
        void start_input_pass(final jpeg_decompress_struct cinfo) {
            cinfo.input_iMCU_row = 0;
            this.start_iMCU_row(cinfo);
        }
        
        void start_iMCU_row(final jpeg_decompress_struct cinfo) {
            final jpeg_d_coef_controller coef = cinfo.coef;
            if (cinfo.comps_in_scan > 1) {
                coef.MCU_rows_per_iMCU_row = 1;
            }
            else if (cinfo.input_iMCU_row < cinfo.total_iMCU_rows - 1) {
                coef.MCU_rows_per_iMCU_row = cinfo.cur_comp_info[0].v_samp_factor;
            }
            else {
                coef.MCU_rows_per_iMCU_row = cinfo.cur_comp_info[0].last_row_height;
            }
            coef.MCU_ctr = 0;
            coef.MCU_vert_offset = 0;
        }
    }
    
    abstract static class jpeg_entropy_decoder
    {
        boolean insufficient_data;
        bitread_working_state br_state_local;
        savable_state state_local;
        
        jpeg_entropy_decoder() {
            this.br_state_local = new bitread_working_state();
            this.state_local = new savable_state();
        }
        
        abstract void start_pass(final jpeg_decompress_struct p0);
        
        abstract boolean decode_mcu(final jpeg_decompress_struct p0, final short[][] p1);
    }
    
    static final class huff_entropy_decoder extends jpeg_entropy_decoder
    {
        bitread_perm_state bitstate;
        savable_state saved;
        int restarts_to_go;
        d_derived_tbl[] dc_derived_tbls;
        d_derived_tbl[] ac_derived_tbls;
        d_derived_tbl[] dc_cur_tbls;
        d_derived_tbl[] ac_cur_tbls;
        boolean[] dc_needed;
        boolean[] ac_needed;
        
        huff_entropy_decoder() {
            this.bitstate = new bitread_perm_state();
            this.saved = new savable_state();
            this.dc_derived_tbls = new d_derived_tbl[4];
            this.ac_derived_tbls = new d_derived_tbl[4];
            this.dc_cur_tbls = new d_derived_tbl[10];
            this.ac_cur_tbls = new d_derived_tbl[10];
            this.dc_needed = new boolean[10];
            this.ac_needed = new boolean[10];
        }
        
        @Override
        void start_pass(final jpeg_decompress_struct cinfo) {
            this.start_pass_huff_decoder(cinfo);
        }
        
        @Override
        boolean decode_mcu(final jpeg_decompress_struct cinfo, final short[][] MCU_data) {
            final huff_entropy_decoder entropy = this;
            final bitread_working_state br_state = this.br_state_local;
            final savable_state state = this.state_local;
            if (cinfo.restart_interval != 0 && entropy.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!entropy.insufficient_data) {
                br_state.cinfo = cinfo;
                br_state.buffer = cinfo.buffer;
                br_state.bytes_in_buffer = cinfo.bytes_in_buffer;
                br_state.bytes_offset = cinfo.bytes_offset;
                int get_buffer = entropy.bitstate.get_buffer;
                int bits_left = entropy.bitstate.bits_left;
                state.last_dc_val[0] = entropy.saved.last_dc_val[0];
                state.last_dc_val[1] = entropy.saved.last_dc_val[1];
                state.last_dc_val[2] = entropy.saved.last_dc_val[2];
                state.last_dc_val[3] = entropy.saved.last_dc_val[3];
                for (int blkn = 0; blkn < cinfo.blocks_in_MCU; ++blkn) {
                    final short[] block = MCU_data[blkn];
                    final d_derived_tbl dctbl = entropy.dc_cur_tbls[blkn];
                    final d_derived_tbl actbl = entropy.ac_cur_tbls[blkn];
                    int s = 0;
                    int nb = 0;
                    if (bits_left < 8) {
                        if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 0)) {
                            return false;
                        }
                        get_buffer = br_state.get_buffer;
                        bits_left = br_state.bits_left;
                        if (bits_left < 8) {
                            nb = 1;
                            if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, dctbl, nb)) < 0) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                        }
                    }
                    if (nb != 1) {
                        final int look = get_buffer >> bits_left - 8 & 0xFF;
                        if ((nb = dctbl.look_nbits[look]) != 0) {
                            bits_left -= nb;
                            s = (dctbl.look_sym[look] & 0xFF);
                        }
                        else {
                            nb = 9;
                            if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, dctbl, nb)) < 0) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                        }
                    }
                    if (s != 0) {
                        if (bits_left < s) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, s)) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                        }
                        final int r = get_buffer >> (bits_left -= s) & (1 << s) - 1;
                        s = ((r < JPEGDecoder.extend_test[s]) ? (r + JPEGDecoder.extend_offset[s]) : r);
                    }
                    if (entropy.dc_needed[blkn]) {
                        final int ci = cinfo.MCU_membership[blkn];
                        s += state.last_dc_val[ci];
                        state.last_dc_val[ci] = s;
                        block[0] = (short)s;
                    }
                    if (entropy.ac_needed[blkn]) {
                        for (int k = 1; k < 64; ++k) {
                            nb = 0;
                            if (bits_left < 8) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 0)) {
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                                if (bits_left < 8) {
                                    nb = 1;
                                    if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, actbl, nb)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                            }
                            if (nb != 1) {
                                final int look2 = get_buffer >> bits_left - 8 & 0xFF;
                                if ((nb = actbl.look_nbits[look2]) != 0) {
                                    bits_left -= nb;
                                    s = (actbl.look_sym[look2] & 0xFF);
                                }
                                else {
                                    nb = 9;
                                    if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, actbl, nb)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                            }
                            int r2 = s >> 4;
                            s &= 0xF;
                            if (s != 0) {
                                k += r2;
                                if (bits_left < s) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, s)) {
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                                r2 = (get_buffer >> (bits_left -= s) & (1 << s) - 1);
                                s = ((r2 < JPEGDecoder.extend_test[s]) ? (r2 + JPEGDecoder.extend_offset[s]) : r2);
                                block[JPEGDecoder.jpeg_natural_order[k]] = (short)s;
                            }
                            else {
                                if (r2 != 15) {
                                    break;
                                }
                                k += 15;
                            }
                        }
                    }
                    else {
                        for (int k = 1; k < 64; ++k) {
                            nb = 0;
                            if (bits_left < 8) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 0)) {
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                                if (bits_left < 8) {
                                    nb = 1;
                                    if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, actbl, nb)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                            }
                            if (nb != 1) {
                                final int look2 = get_buffer >> bits_left - 8 & 0xFF;
                                if ((nb = actbl.look_nbits[look2]) != 0) {
                                    bits_left -= nb;
                                    s = (actbl.look_sym[look2] & 0xFF);
                                }
                                else {
                                    nb = 9;
                                    if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, actbl, nb)) < 0) {
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                            }
                            final int r2 = s >> 4;
                            s &= 0xF;
                            if (s != 0) {
                                k += r2;
                                if (bits_left < s) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, s)) {
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                                bits_left -= s;
                            }
                            else {
                                if (r2 != 15) {
                                    break;
                                }
                                k += 15;
                            }
                        }
                    }
                }
                cinfo.buffer = br_state.buffer;
                cinfo.bytes_in_buffer = br_state.bytes_in_buffer;
                cinfo.bytes_offset = br_state.bytes_offset;
                entropy.bitstate.get_buffer = get_buffer;
                entropy.bitstate.bits_left = bits_left;
                entropy.saved.last_dc_val[0] = state.last_dc_val[0];
                entropy.saved.last_dc_val[1] = state.last_dc_val[1];
                entropy.saved.last_dc_val[2] = state.last_dc_val[2];
                entropy.saved.last_dc_val[3] = state.last_dc_val[3];
            }
            final huff_entropy_decoder huff_entropy_decoder2;
            final huff_entropy_decoder huff_entropy_decoder = huff_entropy_decoder2 = entropy;
            --huff_entropy_decoder2.restarts_to_go;
            return true;
        }
        
        void start_pass_huff_decoder(final jpeg_decompress_struct cinfo) {
            final huff_entropy_decoder entropy = this;
            if (cinfo.Ss != 0 || cinfo.Se != 63 || cinfo.Ah != 0 || cinfo.Al != 0) {}
            for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                final jpeg_component_info compptr = cinfo.cur_comp_info[ci];
                final int dctbl = compptr.dc_tbl_no;
                final int actbl = compptr.ac_tbl_no;
                JPEGDecoder.jpeg_make_d_derived_tbl(cinfo, true, dctbl, entropy.dc_derived_tbls[dctbl] = new d_derived_tbl());
                JPEGDecoder.jpeg_make_d_derived_tbl(cinfo, false, actbl, entropy.ac_derived_tbls[actbl] = new d_derived_tbl());
                entropy.saved.last_dc_val[ci] = 0;
            }
            for (int blkn = 0; blkn < cinfo.blocks_in_MCU; ++blkn) {
                final int ci2 = cinfo.MCU_membership[blkn];
                final jpeg_component_info compptr2 = cinfo.cur_comp_info[ci2];
                entropy.dc_cur_tbls[blkn] = entropy.dc_derived_tbls[compptr2.dc_tbl_no];
                entropy.ac_cur_tbls[blkn] = entropy.ac_derived_tbls[compptr2.ac_tbl_no];
                if (compptr2.component_needed) {
                    entropy.dc_needed[blkn] = true;
                    entropy.ac_needed[blkn] = (compptr2.DCT_scaled_size > 1);
                }
                else {
                    entropy.dc_needed[blkn] = (entropy.ac_needed[blkn] = false);
                }
            }
            entropy.bitstate.bits_left = 0;
            entropy.bitstate.get_buffer = 0;
            entropy.insufficient_data = false;
            entropy.restarts_to_go = cinfo.restart_interval;
        }
        
        boolean process_restart(final jpeg_decompress_struct cinfo) {
            final huff_entropy_decoder entropy = this;
            final jpeg_marker_reader marker2;
            final jpeg_marker_reader marker = marker2 = cinfo.marker;
            marker2.discarded_bytes += entropy.bitstate.bits_left / 8;
            entropy.bitstate.bits_left = 0;
            if (!JPEGDecoder.read_restart_marker(cinfo)) {
                return false;
            }
            for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                entropy.saved.last_dc_val[ci] = 0;
            }
            entropy.restarts_to_go = cinfo.restart_interval;
            if (cinfo.unread_marker == 0) {
                entropy.insufficient_data = false;
            }
            return true;
        }
    }
    
    static final class phuff_entropy_decoder extends jpeg_entropy_decoder
    {
        bitread_perm_state bitstate;
        savable_state saved;
        int restarts_to_go;
        d_derived_tbl[] derived_tbls;
        d_derived_tbl ac_derived_tbl;
        int[] newnz_pos;
        
        phuff_entropy_decoder() {
            this.bitstate = new bitread_perm_state();
            this.saved = new savable_state();
            this.derived_tbls = new d_derived_tbl[4];
            this.newnz_pos = new int[64];
        }
        
        @Override
        void start_pass(final jpeg_decompress_struct cinfo) {
            this.start_pass_phuff_decoder(cinfo);
        }
        
        @Override
        boolean decode_mcu(final jpeg_decompress_struct cinfo, final short[][] MCU_data) {
            final boolean is_DC_band = cinfo.Ss == 0;
            if (cinfo.Ah == 0) {
                if (is_DC_band) {
                    return this.decode_mcu_DC_first(cinfo, MCU_data);
                }
                return this.decode_mcu_AC_first(cinfo, MCU_data);
            }
            else {
                if (is_DC_band) {
                    return this.decode_mcu_DC_refine(cinfo, MCU_data);
                }
                return this.decode_mcu_AC_refine(cinfo, MCU_data);
            }
        }
        
        boolean decode_mcu_DC_refine(final jpeg_decompress_struct cinfo, final short[][] MCU_data) {
            final phuff_entropy_decoder entropy = this;
            final int p1 = 1 << cinfo.Al;
            final bitread_working_state br_state = this.br_state_local;
            if (cinfo.restart_interval != 0 && entropy.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            br_state.cinfo = cinfo;
            br_state.buffer = cinfo.buffer;
            br_state.bytes_in_buffer = cinfo.bytes_in_buffer;
            br_state.bytes_offset = cinfo.bytes_offset;
            int get_buffer = entropy.bitstate.get_buffer;
            int bits_left = entropy.bitstate.bits_left;
            for (int blkn = 0; blkn < cinfo.blocks_in_MCU; ++blkn) {
                final short[] block = MCU_data[blkn];
                if (bits_left < 1) {
                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 1)) {
                        return false;
                    }
                    get_buffer = br_state.get_buffer;
                    bits_left = br_state.bits_left;
                }
                if ((get_buffer >> --bits_left & 0x1) != 0x0) {
                    final short[] array = block;
                    final int n = 0;
                    final short[] array2 = array;
                    final int n2 = 0;
                    array2[n2] |= (short)p1;
                }
            }
            cinfo.buffer = br_state.buffer;
            cinfo.bytes_in_buffer = br_state.bytes_in_buffer;
            cinfo.bytes_offset = br_state.bytes_offset;
            entropy.bitstate.get_buffer = get_buffer;
            entropy.bitstate.bits_left = bits_left;
            final phuff_entropy_decoder phuff_entropy_decoder2;
            final phuff_entropy_decoder phuff_entropy_decoder = phuff_entropy_decoder2 = entropy;
            --phuff_entropy_decoder2.restarts_to_go;
            return true;
        }
        
        boolean decode_mcu_AC_refine(final jpeg_decompress_struct cinfo, final short[][] MCU_data) {
            final phuff_entropy_decoder entropy = this;
            final int Se = cinfo.Se;
            final int p1 = 1 << cinfo.Al;
            final int m1 = -1 << cinfo.Al;
            int s = 0;
            final bitread_working_state br_state = this.br_state_local;
            final int[] newnz_pos = entropy.newnz_pos;
            if (cinfo.restart_interval != 0 && entropy.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!entropy.insufficient_data) {
                br_state.cinfo = cinfo;
                br_state.buffer = cinfo.buffer;
                br_state.bytes_in_buffer = cinfo.bytes_in_buffer;
                br_state.bytes_offset = cinfo.bytes_offset;
                int get_buffer = entropy.bitstate.get_buffer;
                int bits_left = entropy.bitstate.bits_left;
                int EOBRUN = entropy.saved.EOBRUN;
                final short[] block = MCU_data[0];
                final d_derived_tbl tbl = entropy.ac_derived_tbl;
                int num_newnz = 0;
                int k = cinfo.Ss;
                if (EOBRUN == 0) {
                    while (k <= Se) {
                        int nb = 0;
                        if (bits_left < 8) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 0)) {
                                while (num_newnz > 0) {
                                    block[newnz_pos[--num_newnz]] = 0;
                                }
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                            if (bits_left < 8) {
                                nb = 1;
                                if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, tbl, nb)) < 0) {
                                    while (num_newnz > 0) {
                                        block[newnz_pos[--num_newnz]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                        }
                        if (nb != 1) {
                            final int look = get_buffer >> bits_left - 8 & 0xFF;
                            if ((nb = tbl.look_nbits[look]) != 0) {
                                bits_left -= nb;
                                s = (tbl.look_sym[look] & 0xFF);
                            }
                            else {
                                nb = 9;
                                if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, tbl, nb)) < 0) {
                                    while (num_newnz > 0) {
                                        block[newnz_pos[--num_newnz]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                        }
                        int r = s >> 4;
                        s &= 0xF;
                        if (s != 0) {
                            if (s != 1) {}
                            if (bits_left < 1) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 1)) {
                                    while (num_newnz > 0) {
                                        block[newnz_pos[--num_newnz]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                            if ((get_buffer >> --bits_left & 0x1) != 0x0) {
                                s = p1;
                            }
                            else {
                                s = m1;
                            }
                        }
                        else if (r != 15) {
                            EOBRUN = 1 << r;
                            if (r != 0) {
                                if (bits_left < r) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, r)) {
                                        while (num_newnz > 0) {
                                            block[newnz_pos[--num_newnz]] = 0;
                                        }
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                                r = (get_buffer >> (bits_left -= r) & (1 << r) - 1);
                                EOBRUN += r;
                                break;
                            }
                            break;
                        }
                        do {
                            final short[] thiscoef = block;
                            final int thiscoef_offset = JPEGDecoder.jpeg_natural_order[k];
                            if (thiscoef[thiscoef_offset] != 0) {
                                if (bits_left < 1) {
                                    if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 1)) {
                                        while (num_newnz > 0) {
                                            block[newnz_pos[--num_newnz]] = 0;
                                        }
                                        return false;
                                    }
                                    get_buffer = br_state.get_buffer;
                                    bits_left = br_state.bits_left;
                                }
                                if ((get_buffer >> --bits_left & 0x1) == 0x0) {
                                    continue;
                                }
                                if ((thiscoef[thiscoef_offset] & p1) != 0x0) {
                                    continue;
                                }
                                if (thiscoef[thiscoef_offset] >= 0) {
                                    final short[] array = thiscoef;
                                    final int n = thiscoef_offset;
                                    final short[] array5 = array;
                                    final int n5 = n;
                                    array5[n5] += (short)p1;
                                }
                                else {
                                    final short[] array2 = thiscoef;
                                    final int n2 = thiscoef_offset;
                                    final short[] array6 = array2;
                                    final int n6 = n2;
                                    array6[n6] += (short)m1;
                                }
                            }
                            else {
                                if (--r < 0) {
                                    break;
                                }
                                continue;
                            }
                        } while (++k <= Se);
                        if (s != 0) {
                            final int pos = JPEGDecoder.jpeg_natural_order[k];
                            block[pos] = (short)s;
                            newnz_pos[num_newnz++] = pos;
                        }
                        ++k;
                    }
                }
                if (EOBRUN > 0) {
                    while (k <= Se) {
                        final short[] thiscoef2 = block;
                        final int thiscoef_offset2 = JPEGDecoder.jpeg_natural_order[k];
                        if (thiscoef2[thiscoef_offset2] != 0) {
                            if (bits_left < 1) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 1)) {
                                    while (num_newnz > 0) {
                                        block[newnz_pos[--num_newnz]] = 0;
                                    }
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                            if ((get_buffer >> --bits_left & 0x1) != 0x0 && (thiscoef2[thiscoef_offset2] & p1) == 0x0) {
                                if (thiscoef2[thiscoef_offset2] >= 0) {
                                    final short[] array3 = thiscoef2;
                                    final int n3 = thiscoef_offset2;
                                    final short[] array7 = array3;
                                    final int n7 = n3;
                                    array7[n7] += (short)p1;
                                }
                                else {
                                    final short[] array4 = thiscoef2;
                                    final int n4 = thiscoef_offset2;
                                    final short[] array8 = array4;
                                    final int n8 = n4;
                                    array8[n8] += (short)m1;
                                }
                            }
                        }
                        ++k;
                    }
                    --EOBRUN;
                }
                cinfo.buffer = br_state.buffer;
                cinfo.bytes_in_buffer = br_state.bytes_in_buffer;
                cinfo.bytes_offset = br_state.bytes_offset;
                entropy.bitstate.get_buffer = get_buffer;
                entropy.bitstate.bits_left = bits_left;
                entropy.saved.EOBRUN = EOBRUN;
            }
            final phuff_entropy_decoder phuff_entropy_decoder2;
            final phuff_entropy_decoder phuff_entropy_decoder = phuff_entropy_decoder2 = entropy;
            --phuff_entropy_decoder2.restarts_to_go;
            return true;
        }
        
        boolean decode_mcu_AC_first(final jpeg_decompress_struct cinfo, final short[][] MCU_data) {
            final phuff_entropy_decoder entropy = this;
            final int Se = cinfo.Se;
            final int Al = cinfo.Al;
            int s = 0;
            final bitread_working_state br_state = this.br_state_local;
            if (cinfo.restart_interval != 0 && entropy.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!entropy.insufficient_data) {
                int EOBRUN = entropy.saved.EOBRUN;
                if (EOBRUN > 0) {
                    --EOBRUN;
                }
                else {
                    br_state.cinfo = cinfo;
                    br_state.buffer = cinfo.buffer;
                    br_state.bytes_in_buffer = cinfo.bytes_in_buffer;
                    br_state.bytes_offset = cinfo.bytes_offset;
                    int get_buffer = entropy.bitstate.get_buffer;
                    int bits_left = entropy.bitstate.bits_left;
                    final short[] block = MCU_data[0];
                    final d_derived_tbl tbl = entropy.ac_derived_tbl;
                    for (int k = cinfo.Ss; k <= Se; ++k) {
                        int nb = 0;
                        if (bits_left < 8) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 0)) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                            if (bits_left < 8) {
                                nb = 1;
                                if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, tbl, nb)) < 0) {
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                        }
                        if (nb != 1) {
                            final int look = get_buffer >> bits_left - 8 & 0xFF;
                            if ((nb = tbl.look_nbits[look]) != 0) {
                                bits_left -= nb;
                                s = (tbl.look_sym[look] & 0xFF);
                            }
                            else {
                                nb = 9;
                                if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, tbl, nb)) < 0) {
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                        }
                        int r = s >> 4;
                        s &= 0xF;
                        if (s != 0) {
                            k += r;
                            if (bits_left < s) {
                                if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, s)) {
                                    return false;
                                }
                                get_buffer = br_state.get_buffer;
                                bits_left = br_state.bits_left;
                            }
                            r = (get_buffer >> (bits_left -= s) & (1 << s) - 1);
                            s = ((r < JPEGDecoder.extend_test[s]) ? (r + JPEGDecoder.extend_offset[s]) : r);
                            block[JPEGDecoder.jpeg_natural_order[k]] = (short)(s << Al);
                        }
                        else {
                            if (r != 15) {
                                EOBRUN = 1 << r;
                                if (r != 0) {
                                    if (bits_left < r) {
                                        if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, r)) {
                                            return false;
                                        }
                                        get_buffer = br_state.get_buffer;
                                        bits_left = br_state.bits_left;
                                    }
                                    r = (get_buffer >> (bits_left -= r) & (1 << r) - 1);
                                    EOBRUN += r;
                                }
                                --EOBRUN;
                                break;
                            }
                            k += 15;
                        }
                    }
                    cinfo.buffer = br_state.buffer;
                    cinfo.bytes_in_buffer = br_state.bytes_in_buffer;
                    cinfo.bytes_offset = br_state.bytes_offset;
                    entropy.bitstate.get_buffer = get_buffer;
                    entropy.bitstate.bits_left = bits_left;
                }
                entropy.saved.EOBRUN = EOBRUN;
            }
            final phuff_entropy_decoder phuff_entropy_decoder2;
            final phuff_entropy_decoder phuff_entropy_decoder = phuff_entropy_decoder2 = entropy;
            --phuff_entropy_decoder2.restarts_to_go;
            return true;
        }
        
        boolean decode_mcu_DC_first(final jpeg_decompress_struct cinfo, final short[][] MCU_data) {
            final phuff_entropy_decoder entropy = this;
            final int Al = cinfo.Al;
            int s = 0;
            final bitread_working_state br_state = this.br_state_local;
            final savable_state state = this.state_local;
            if (cinfo.restart_interval != 0 && entropy.restarts_to_go == 0 && !this.process_restart(cinfo)) {
                return false;
            }
            if (!entropy.insufficient_data) {
                br_state.cinfo = cinfo;
                br_state.buffer = cinfo.buffer;
                br_state.bytes_in_buffer = cinfo.bytes_in_buffer;
                br_state.bytes_offset = cinfo.bytes_offset;
                int get_buffer = entropy.bitstate.get_buffer;
                int bits_left = entropy.bitstate.bits_left;
                state.EOBRUN = entropy.saved.EOBRUN;
                state.last_dc_val[0] = entropy.saved.last_dc_val[0];
                state.last_dc_val[1] = entropy.saved.last_dc_val[1];
                state.last_dc_val[2] = entropy.saved.last_dc_val[2];
                state.last_dc_val[3] = entropy.saved.last_dc_val[3];
                for (int blkn = 0; blkn < cinfo.blocks_in_MCU; ++blkn) {
                    final short[] block = MCU_data[blkn];
                    final int ci = cinfo.MCU_membership[blkn];
                    final jpeg_component_info compptr = cinfo.cur_comp_info[ci];
                    final d_derived_tbl tbl = entropy.derived_tbls[compptr.dc_tbl_no];
                    int nb = 0;
                    if (bits_left < 8) {
                        if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, 0)) {
                            return false;
                        }
                        get_buffer = br_state.get_buffer;
                        bits_left = br_state.bits_left;
                        if (bits_left < 8) {
                            nb = 1;
                            if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, tbl, nb)) < 0) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                        }
                    }
                    if (nb != 1) {
                        final int look = get_buffer >> bits_left - 8 & 0xFF;
                        if ((nb = tbl.look_nbits[look]) != 0) {
                            bits_left -= nb;
                            s = (tbl.look_sym[look] & 0xFF);
                        }
                        else {
                            nb = 9;
                            if ((s = JPEGDecoder.jpeg_huff_decode(br_state, get_buffer, bits_left, tbl, nb)) < 0) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                        }
                    }
                    if (s != 0) {
                        if (bits_left < s) {
                            if (!JPEGDecoder.jpeg_fill_bit_buffer(br_state, get_buffer, bits_left, s)) {
                                return false;
                            }
                            get_buffer = br_state.get_buffer;
                            bits_left = br_state.bits_left;
                        }
                        final int r = get_buffer >> (bits_left -= s) & (1 << s) - 1;
                        s = ((r < JPEGDecoder.extend_test[s]) ? (r + JPEGDecoder.extend_offset[s]) : r);
                    }
                    s += state.last_dc_val[ci];
                    state.last_dc_val[ci] = s;
                    block[0] = (short)(s << Al);
                }
                cinfo.buffer = br_state.buffer;
                cinfo.bytes_in_buffer = br_state.bytes_in_buffer;
                cinfo.bytes_offset = br_state.bytes_offset;
                entropy.bitstate.get_buffer = get_buffer;
                entropy.bitstate.bits_left = bits_left;
                entropy.saved.EOBRUN = state.EOBRUN;
                entropy.saved.last_dc_val[0] = state.last_dc_val[0];
                entropy.saved.last_dc_val[1] = state.last_dc_val[1];
                entropy.saved.last_dc_val[2] = state.last_dc_val[2];
                entropy.saved.last_dc_val[3] = state.last_dc_val[3];
            }
            final phuff_entropy_decoder phuff_entropy_decoder2;
            final phuff_entropy_decoder phuff_entropy_decoder = phuff_entropy_decoder2 = entropy;
            --phuff_entropy_decoder2.restarts_to_go;
            return true;
        }
        
        boolean process_restart(final jpeg_decompress_struct cinfo) {
            final phuff_entropy_decoder entropy = this;
            final jpeg_marker_reader marker2;
            final jpeg_marker_reader marker = marker2 = cinfo.marker;
            marker2.discarded_bytes += entropy.bitstate.bits_left / 8;
            entropy.bitstate.bits_left = 0;
            if (!JPEGDecoder.read_restart_marker(cinfo)) {
                return false;
            }
            for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                entropy.saved.last_dc_val[ci] = 0;
            }
            entropy.saved.EOBRUN = 0;
            entropy.restarts_to_go = cinfo.restart_interval;
            if (cinfo.unread_marker == 0) {
                entropy.insufficient_data = false;
            }
            return true;
        }
        
        void start_pass_phuff_decoder(final jpeg_decompress_struct cinfo) {
            final phuff_entropy_decoder entropy = this;
            final boolean is_DC_band = cinfo.Ss == 0;
            boolean bad = false;
            if (is_DC_band) {
                if (cinfo.Se != 0) {
                    bad = true;
                }
            }
            else {
                if (cinfo.Ss > cinfo.Se || cinfo.Se >= 64) {
                    bad = true;
                }
                if (cinfo.comps_in_scan != 1) {
                    bad = true;
                }
            }
            if (cinfo.Ah != 0 && cinfo.Al != cinfo.Ah - 1) {
                bad = true;
            }
            if (cinfo.Al > 13) {
                bad = true;
            }
            if (bad) {
                JPEGDecoder.error();
            }
            for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                final int cindex = cinfo.cur_comp_info[ci].component_index;
                final int[] coef_bit_ptr = cinfo.coef_bits[cindex];
                if (is_DC_band || coef_bit_ptr[0] < 0) {}
                for (int coefi = cinfo.Ss; coefi <= cinfo.Se; ++coefi) {
                    final int expected = (coef_bit_ptr[coefi] < 0) ? 0 : coef_bit_ptr[coefi];
                    if (cinfo.Ah != expected) {}
                    coef_bit_ptr[coefi] = cinfo.Al;
                }
            }
            for (int ci = 0; ci < cinfo.comps_in_scan; ++ci) {
                final jpeg_component_info compptr = cinfo.cur_comp_info[ci];
                if (is_DC_band) {
                    if (cinfo.Ah == 0) {
                        final int tbl = compptr.dc_tbl_no;
                        JPEGDecoder.jpeg_make_d_derived_tbl(cinfo, true, tbl, entropy.derived_tbls[tbl] = new d_derived_tbl());
                    }
                }
                else {
                    final int tbl = compptr.ac_tbl_no;
                    JPEGDecoder.jpeg_make_d_derived_tbl(cinfo, false, tbl, entropy.derived_tbls[tbl] = new d_derived_tbl());
                    entropy.ac_derived_tbl = entropy.derived_tbls[tbl];
                }
                entropy.saved.last_dc_val[ci] = 0;
            }
            entropy.bitstate.bits_left = 0;
            entropy.bitstate.get_buffer = 0;
            entropy.insufficient_data = false;
            entropy.saved.EOBRUN = 0;
            entropy.restarts_to_go = cinfo.restart_interval;
        }
    }
    
    static final class jpeg_component_info
    {
        int component_id;
        int component_index;
        int h_samp_factor;
        int v_samp_factor;
        int quant_tbl_no;
        int dc_tbl_no;
        int ac_tbl_no;
        int width_in_blocks;
        int height_in_blocks;
        int DCT_scaled_size;
        int downsampled_width;
        int downsampled_height;
        boolean component_needed;
        int MCU_width;
        int MCU_height;
        int MCU_blocks;
        int MCU_sample_width;
        int last_col_width;
        int last_row_height;
        JQUANT_TBL quant_table;
        int[] dct_table;
    }
    
    static final class jpeg_color_quantizer
    {
        int[][] sv_colormap;
        int sv_actual;
        int[][] colorindex;
        boolean is_padded;
        int[] Ncolors;
        int row_index;
        boolean on_odd_row;
        
        jpeg_color_quantizer() {
            this.Ncolors = new int[4];
        }
        
        void start_pass(final jpeg_decompress_struct cinfo, final boolean is_pre_scan) {
            JPEGDecoder.error();
        }
    }
    
    static final class jpeg_upsampler
    {
        boolean need_context_rows;
        byte[][][] color_buf;
        int[] color_buf_offset;
        int[] methods;
        int next_row_out;
        int rows_to_go;
        int[] rowgroup_height;
        byte[] h_expand;
        byte[] v_expand;
        
        jpeg_upsampler() {
            this.color_buf = new byte[10][][];
            this.color_buf_offset = new int[10];
            this.methods = new int[10];
            this.rowgroup_height = new int[10];
            this.h_expand = new byte[10];
            this.v_expand = new byte[10];
        }
        
        void start_pass(final jpeg_decompress_struct cinfo) {
            final jpeg_upsampler upsample = cinfo.upsample;
            upsample.next_row_out = cinfo.max_v_samp_factor;
            upsample.rows_to_go = cinfo.output_height;
        }
    }
    
    static final class jpeg_marker_reader
    {
        boolean saw_SOI;
        boolean saw_SOF;
        int next_restart_num;
        int discarded_bytes;
        int length_limit_COM;
        int[] length_limit_APPn;
        
        jpeg_marker_reader() {
            this.length_limit_APPn = new int[16];
        }
    }
    
    static final class jpeg_d_main_controller
    {
        int process_data;
        byte[][][] buffer;
        int[] buffer_offset;
        boolean buffer_full;
        int[] rowgroup_ctr;
        byte[][][][] xbuffer;
        int[][] xbuffer_offset;
        int whichptr;
        int context_state;
        int rowgroups_avail;
        int iMCU_row_ctr;
        
        jpeg_d_main_controller() {
            this.buffer = new byte[10][][];
            this.buffer_offset = new int[10];
            this.rowgroup_ctr = new int[1];
            this.xbuffer = new byte[2][][][];
            this.xbuffer_offset = new int[2][];
        }
        
        void start_pass(final jpeg_decompress_struct cinfo, final int pass_mode) {
            final jpeg_d_main_controller main = cinfo.main;
            switch (pass_mode) {
                case 0: {
                    if (cinfo.upsample.need_context_rows) {
                        main.process_data = 1;
                        JPEGDecoder.make_funny_pointers(cinfo);
                        main.whichptr = 0;
                        main.context_state = 0;
                        main.iMCU_row_ctr = 0;
                    }
                    else {
                        main.process_data = 0;
                    }
                    main.buffer_full = false;
                    main.rowgroup_ctr[0] = 0;
                    break;
                }
                default: {
                    JPEGDecoder.error();
                    break;
                }
            }
        }
    }
    
    static final class jpeg_decomp_master
    {
        boolean is_dummy_pass;
        int pass_number;
        boolean using_merged_upsample;
        jpeg_color_quantizer quantizer_1pass;
        jpeg_color_quantizer quantizer_2pass;
    }
    
    static final class jpeg_inverse_dct
    {
        int[] cur_method;
        
        jpeg_inverse_dct() {
            this.cur_method = new int[10];
        }
        
        void start_pass(final jpeg_decompress_struct cinfo) {
            final jpeg_inverse_dct idct = cinfo.idct;
            int method = 0;
            for (int ci = 0; ci < cinfo.num_components; ++ci) {
                final jpeg_component_info compptr = cinfo.comp_info[ci];
                Label_0090: {
                    switch (compptr.DCT_scaled_size) {
                        case 8: {
                            switch (cinfo.dct_method) {
                                case 0: {
                                    method = 0;
                                    break Label_0090;
                                }
                                default: {
                                    JPEGDecoder.error();
                                    break Label_0090;
                                }
                            }
                            break;
                        }
                        default: {
                            JPEGDecoder.error();
                            break;
                        }
                    }
                }
                if (compptr.component_needed && idct.cur_method[ci] != method) {
                    final JQUANT_TBL qtbl = compptr.quant_table;
                    if (qtbl != null) {
                        switch (idct.cur_method[ci] = method) {
                            case 0: {
                                final int[] ismtbl = compptr.dct_table;
                                for (int i = 0; i < 64; ++i) {
                                    ismtbl[i] = qtbl.quantval[i];
                                }
                                break;
                            }
                            default: {
                                JPEGDecoder.error();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    static final class jpeg_input_controller
    {
        int consume_input;
        boolean has_multiple_scans;
        boolean eoi_reached;
        boolean inheaders;
    }
    
    static final class jpeg_color_deconverter
    {
        int color_convert;
        int[] Cr_r_tab;
        int[] Cb_b_tab;
        int[] Cr_g_tab;
        int[] Cb_g_tab;
        
        void start_pass(final jpeg_decompress_struct cinfo) {
        }
    }
    
    static final class jpeg_d_post_controller
    {
        int post_process_data;
        int[] whole_image;
        int[][] buffer;
        int strip_height;
        int starting_row;
        int next_row;
        
        void start_pass(final jpeg_decompress_struct cinfo, final int pass_mode) {
            final jpeg_d_post_controller post = cinfo.post;
            switch (pass_mode) {
                case 0: {
                    if (cinfo.quantize_colors) {
                        JPEGDecoder.error(20);
                        break;
                    }
                    post.post_process_data = 1;
                    break;
                }
                default: {
                    JPEGDecoder.error();
                    break;
                }
            }
            final jpeg_d_post_controller jpeg_d_post_controller = post;
            final jpeg_d_post_controller jpeg_d_post_controller2 = post;
            final int n = 0;
            jpeg_d_post_controller2.next_row = 0;
            jpeg_d_post_controller.starting_row = 0;
        }
    }
    
    static final class jpeg_decompress_struct
    {
        boolean is_decompressor;
        int global_state;
        InputStream inputStream;
        byte[] buffer;
        int bytes_in_buffer;
        int bytes_offset;
        boolean start_of_file;
        int image_width;
        int image_height;
        int num_components;
        int jpeg_color_space;
        int out_color_space;
        int scale_num;
        int scale_denom;
        double output_gamma;
        boolean buffered_image;
        boolean raw_data_out;
        int dct_method;
        boolean do_fancy_upsampling;
        boolean do_block_smoothing;
        boolean quantize_colors;
        int dither_mode;
        boolean two_pass_quantize;
        int desired_number_of_colors;
        boolean enable_1pass_quant;
        boolean enable_external_quant;
        boolean enable_2pass_quant;
        int output_width;
        int output_height;
        int out_color_components;
        int output_components;
        int rec_outbuf_height;
        int actual_number_of_colors;
        int[] colormap;
        int output_scanline;
        int input_scan_number;
        int input_iMCU_row;
        int output_scan_number;
        int output_iMCU_row;
        int[][] coef_bits;
        JQUANT_TBL[] quant_tbl_ptrs;
        JHUFF_TBL[] dc_huff_tbl_ptrs;
        JHUFF_TBL[] ac_huff_tbl_ptrs;
        int data_precision;
        jpeg_component_info[] comp_info;
        boolean progressive_mode;
        boolean arith_code;
        byte[] arith_dc_L;
        byte[] arith_dc_U;
        byte[] arith_ac_K;
        int restart_interval;
        boolean saw_JFIF_marker;
        byte JFIF_major_version;
        byte JFIF_minor_version;
        byte density_unit;
        short X_density;
        short Y_density;
        boolean saw_Adobe_marker;
        byte Adobe_transform;
        boolean CCIR601_sampling;
        jpeg_marker_reader marker_list;
        int max_h_samp_factor;
        int max_v_samp_factor;
        int min_DCT_scaled_size;
        int total_iMCU_rows;
        byte[] sample_range_limit;
        int sample_range_limit_offset;
        int comps_in_scan;
        jpeg_component_info[] cur_comp_info;
        int MCUs_per_row;
        int MCU_rows_in_scan;
        int blocks_in_MCU;
        int[] MCU_membership;
        int Ss;
        int Se;
        int Ah;
        int Al;
        int unread_marker;
        int[] workspace;
        int[] row_ctr;
        jpeg_decomp_master master;
        jpeg_d_main_controller main;
        jpeg_d_coef_controller coef;
        jpeg_d_post_controller post;
        jpeg_input_controller inputctl;
        jpeg_marker_reader marker;
        jpeg_entropy_decoder entropy;
        jpeg_inverse_dct idct;
        jpeg_upsampler upsample;
        jpeg_color_deconverter cconvert;
        jpeg_color_quantizer cquantize;
        
        jpeg_decompress_struct() {
            this.quant_tbl_ptrs = new JQUANT_TBL[4];
            this.dc_huff_tbl_ptrs = new JHUFF_TBL[4];
            this.ac_huff_tbl_ptrs = new JHUFF_TBL[4];
            this.arith_dc_L = new byte[16];
            this.arith_dc_U = new byte[16];
            this.arith_ac_K = new byte[16];
            this.cur_comp_info = new jpeg_component_info[4];
            this.MCU_membership = new int[10];
            this.workspace = new int[64];
            this.row_ctr = new int[1];
        }
    }
}
